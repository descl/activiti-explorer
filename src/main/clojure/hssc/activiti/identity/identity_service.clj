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

(defn -createGroupQuery
  [_])

(def ^:static -getInstance (constantly (new hssc.activiti.identity.IdentityServiceImpl)))
