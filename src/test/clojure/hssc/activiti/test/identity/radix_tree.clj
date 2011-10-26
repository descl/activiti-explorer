(ns hssc.activiti.test.identity.radix-tree
  (:use clojure.test
        hssc.activiti.identity.radix-tree))

(deftest trie-test
  (is (empty? (trie-prefix-get empty-trie "foo")))
  (is (zero? (trie-size empty-trie)))
  (is (= ["bar"] (trie-prefix-get (trie-assoc empty-trie "foo" "bar") "foo")))
  (let [t (-> empty-trie
            (trie-assoc "foo" "bar")
            (trie-assoc "foob" "barb")
            (trie-assoc "baseball" "Basketball")
            (trie-assoc "fooey " "blooz"))]
    (is (= 4 (trie-size t)))
    (is (= (trie-prefix-get t "foob") ["barb"]))
    (is (= (set (trie-prefix-get t "foo")) #{"bar" "barb" "blooz"}))
    (let [t' (trie-dissoc t "fooey ")]
      (is (= 3 (trie-size t')))
      (is (= (set (trie-prefix-get t' "foo")) #{"bar" "barb"}))
      (is (empty? (trie-prefix-get t' "fooey"))))))

(deftest radix-tree-test
  (is (create-empty-radix-tree))
  (let [rt (create-empty-radix-tree)]
    (is (= (.getSize rt) 0))
    (.insert rt "Jed" :jed)
    (is (= (.find rt "Jed") :jed))
    (.delete rt "Jed")
    (is (nil? (.find rt "Jed")))))
