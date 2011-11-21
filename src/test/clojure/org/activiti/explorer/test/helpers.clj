(ns org.activiti.explorer.test.helpers
  (:require [clj-http.client :as client])
  (:use clojure.test)
  (:use [org.activiti.explorer.test.servlet :only [run-jetty]]))

(def port 51624) ; random
(def ^:dynamic *server* nil)

(defn- start-test-server
  []
  (run-jetty {:port port, :join? false}))

(defn- stop-test-server
  [server]
  (.stop server))

(defn run-web-test
  [f]
  (binding [*server* (start-test-server)]
    (try
      (f)
      (finally
        (stop-test-server *server*)))))

(defmacro defwebtest
  [name & body]
  `(deftest ~name (run-web-test (fn [] ~@body))))

(defn get
  [path]
  (client/get (str "http://localhost:" port path)))

(defwebtest webtest-test
  (is (get "/")))
