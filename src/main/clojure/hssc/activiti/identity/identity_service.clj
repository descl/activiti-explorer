(ns hssc.activiti.identity.identity-service
  (:use [hssc.util :only [def-bean-maker]])
  (:require [clojure.java.io :as jio])
  (:import (org.activiti.engine.identity
            GroupQuery
            UserQuery
            User
            Group
            Picture))
  (:gen-class
    :name hssc.activiti.identity.IdentityServiceImpl
    :implements [org.activiti.engine.IdentityService]
    :methods [[getInstance [] org.activiti.engine.IdentityService]]))



(deftype GroupQueryImpl [info]
  GroupQuery
  (groupId [_ group-id] _)
  (groupMember [_ group-member-user-id] _)
  (groupName [_ group-name] _)
  (groupNameLike [_ group-name-like] _)
  (groupType [_ group-type] _)
  (orderByGroupId [_] _)
  (orderByGroupName [_] _)
  (orderByGroupType [_] _)
  (asc [_] _)
  (count [_] 0)
  (desc [_] _)
  (list [_] [])
  (listPage [_ first-result max-results] [])
  (singleResult [_]))

(deftype UserQueryImpl [info]
  UserQuery
  (memberOfGroup [_ group-id] _)
  (orderByUserEmail [_] _)
  (orderByUserFirstName [_] _)
  (orderByUserId [_] _)
  (orderByUserLastName [_] _)
  (userEmail [_ email] _)
  (userEmailLike [_ email-like] _)
  (userFirstName [_ first-name] _)
  (userFirstNameLike [_ first-name-like] _)
  (userId [_ user-id] _)
  (userLastName [_ last-name] _)
  (userLastNameLike [_ last-name-like] _)
  (asc [_] _)
  (count [_] 0)
  (desc [_] _)
  (list [_] [])
  (listPage [_ first-result max-results] [])
  (singleResult [_]))

(def-bean-maker make-user
  User
  email first-name id last-name password)

(def-bean-maker make-group
  Group)

; Temporary implementations of the IdentityService methods that
; gives good error information.
(defmacro defsn
  [& names]
  (cons 'do
        (for [name names]
          `(defn ~(symbol (str "-" name)) [& args#]
            (throw (new Exception (str "Stub method called: IdentityService#" '~name)))))))

(defsn
 checkPassword
 createGroupQuery
 createMembership
 createUserQuery
 deleteGroup
 deleteMembership
 deleteUser
 deleteUserAccount
 deleteUserInfo
 getUserAccount
 getUserAccountNames
 getUserInfo
 getUserInfoKeys
 getUserPicture
 newGroup
 newUser
 saveGroup
 saveUser
 setAuthenticatedUserId
 setUserAccount
 setUserInfo
 setUserPicture)

(defn resource-bytes
  "Returns a byte-array."
  [path]
  (let [is (jio/input-stream (jio/resource path))]
    (loop [bs [], next-byte (.read is)]
      (if (neg? next-byte)
        (let [ba (make-array Byte/TYPE (count bs))]
          (doseq [[int i] (map vector bs (range))] (aset ba i (.byteValue int)))
          ba)
        (recur (conj bs next-byte) (.read is))))))


(def fozzie-picture
  (new Picture
    (resource-bytes "org/activiti/explorer/images/fozzie.jpg")
    "image/jpg"))

(defn -createGroupQuery [_] (new GroupQueryImpl {}))
(defn -createUserQuery  [_] (new UserQueryImpl  {}))
(defn -getUserPicture   [_ user-id] fozzie-picture)

(def get-instance (constantly (new hssc.activiti.identity.IdentityServiceImpl)))
