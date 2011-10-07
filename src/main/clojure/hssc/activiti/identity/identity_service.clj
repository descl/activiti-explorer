(ns hssc.activiti.identity.identity-service
  (:gen-class
    :name hssc.activiti.identity.IdentityServiceImpl
    :implements [org.activiti.engine.IdentityService]
    :methods [[getInstance [] org.activiti.engine.IdentityService]]))

(defrecord GroupQueryImpl [info]
  org.activiti.engine.identity.GroupQuery
  (groupId [_ group-id])
  (groupMember [_ group-member-user-id])
  (groupName [_ group-name])
  (groupNameLike [_ group-name-like])
  (groupType [_ group-type])
  (orderByGroupId [_])
  (orderByGroupName [_])
  (orderByGroupType [_])
  (asc [_])
  (count [_])
  (desc [_])
  (list [_])
  (listPage [_ first-result max-results])
  (singleResult [_]))

(defmacro defsn
  [& names]
  (cons 'do
        (for [name names]
          `(defn ~(symbol (str "-" name)) [& args#]
            (throw (new Exception "That felt good."))))))

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

(def get-instance (constantly (new hssc.activiti.identity.IdentityServiceImpl)))
