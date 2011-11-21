(ns org.activiti.explorer.test.servlet
  "Most of this code was pillaged from the ring jetty adapter.
  Specifically, https://github.com/mmcgrana/ring/blob/bbd36d0a0af08529d417b8b13e48f29c6948c680/ring-jetty-adapter/src/ring/adapter/jetty.clj"
  (:import (org.mortbay.jetty.handler AbstractHandler)
           (org.mortbay.jetty Server Request Response)
           (org.mortbay.jetty.servlet ServletHandler)
           (org.mortbay.jetty.bio SocketConnector)
           (org.mortbay.jetty.security SslSocketConnector)
           (javax.servlet.http HttpServletRequest HttpServletResponse)
           (org.activiti.explorer.servlet ExplorerApplicationServlet)))

(defn- activiti-explorer-handler
  []
  (let [sh (new ServletHandler),
        sholder (.newServletHolder sh ExplorerApplicationServlet)]
    (.addServlet sh sholder)
    sh))

(defn- create-server
  "Construct a Jetty Server instance."
  [options]
  (let [connector (doto (SocketConnector.)
                    (.setPort (options :port 80))
                    (.setHost (options :host)))
        server    (doto (Server.)
                    (.addConnector connector)
                    (.setSendDateHeader true))]
    server))

(defn ^Server run-jetty
  "Start a Jetty webserver to serve the given handler according to the
  supplied options:

  :configurator - a function called with the Jetty Server instance
  :port         - the port to listen on (defaults to 80)
  :host         - the hostname to listen on
  :join?        - blocks the thread until server ends (defaults to true)"
  [options]
  (let [^Server s (create-server (dissoc options :configurator))]
    (when-let [configurator (:configurator options)]
      (configurator s))
    (doto s
      (.addHandler (activiti-explorer-handler))
      (.start))
    (when (:join? options true)
      (.join s))
    s))
