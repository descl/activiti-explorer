(ns hssc.activiti.identity.login-handler
  (:import javax.servlet.http.HttpServletRequest)
  (:require [obis-shared.shibboleth :as shib])
  (:gen-class
    :name hssc.activiti.identity.LoginHandler
    :implements [org.activiti.explorer.ui.login.LoginHandler]))



(defn -onRequestStart [& args])
(defn -onRequestEnd [& args])
(defn -logout [& args])

(defrecord ShibUser [uid first-name last-name full-name]
  org.activiti.explorer.identity.LoggedInUser
  (getId [_] uid)
  (getFirstName [_] first-name)
  (getLastName  [_] last-name)
  (getFullName  [_] full-name)
  (getPassword  [_])
  ; I think we're fine to leave these hardcoded, as I don't think
  ; we need admin users, especially in the sense of managing the users/groups
  (isAdmin      [_] false)
  (isUser       [_] true))

(defn header-map
  [http-req]
  (let [header-names (enumeration-seq (.getHeaderNames http-req))]
    (zipmap header-names (for [hn header-names] (.getHeader http-req hn)))))

(defn -authenticate
  [_ req resp]
  ; The interface authenticate method is overloaded, and if the args
  ; are a different type we just return nil
  (when (instance? HttpServletRequest req)
    (when-let [ident (shib/headers-to-identity (header-map req))]
      (let [{:keys [first_name last_name uid]} (:attributes ident)]
        (new ShibUser uid first_name last_name (str first_name " " last_name))))))
