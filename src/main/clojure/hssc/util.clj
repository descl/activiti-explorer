(ns hssc.util
  (:require [inflections.core :as inf]))

(let [prop-name-to-method-name
        (fn [prop-name]
          (->> prop-name
            name
            inf/underscore
            inf/camelize
            (str "get")
            symbol))]
  (defmacro def-bean-maker
    "Defs a function that takes a map and returns an implementation
    of the given interface (via reify) that implements getters for
    the property names given by looking them up in the map.

    e.g.,
      (def-bean-maker make-user
        User
        email first-name id last-name password)
      ; => creates a function called make-user
      (make-user {:first-name \"FOO\" :last-name \"BAR\"})
      ; => returns an object implementing User"
    [function-name interface-name & property-names]
    (let [arg-name (gensym)]
      `(defn ~function-name
        [~arg-name]
        (reify ~interface-name
          ~@(for [prop-name property-names]
             `(~(prop-name-to-method-name prop-name)
                [_#]
                (get ~arg-name ~(keyword prop-name)))))))))
