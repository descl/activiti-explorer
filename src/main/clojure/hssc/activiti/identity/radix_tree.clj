(ns hssc.activiti.identity.radix-tree
  (:require [clojure.walk :as wk])
  (:import java.util.ArrayList)
  (:import org.activiti.explorer.cache.DuplicateKeyException)
  (:import org.activiti.explorer.cache.RadixTree))

; Basic implementation of a persistent immutable prefix trie. Can use
; strings as keys or any seq really.
(def empty-trie {::size 0})

(defn trie-contains?
  [t k]
  (contains? (get-in t k) ::value))

(defn trie-dissoc
  [t k]
  (if (empty? k)
    (if (contains? t ::value)
      (update-in (dissoc t ::value) [::size] dec)
      t)
    (let [[k-first & k-rest] k,
          child (t k-first),
          child' (trie-dissoc child k-rest)]
      (if (= (::size child) (::size child'))
        t
        (update-in (assoc t k-first child') [::size] dec)))))

; trie-get returns a lazy seq
(letfn [(get-all-vals [t]
          (let [child-vals (mapcat get-all-vals (vals (dissoc t ::value ::size)))]
            (if (contains? t ::value)
              (cons (t ::value) child-vals)
              child-vals)))]
  (defn trie-prefix-get
    [t k]
    (get-all-vals (get-in t k))))

(defn trie-get
  [t k]
  (::value (get-in t k)))

(defn trie-assoc
  [t k v]
  (if (empty? k)
    (if (contains? t ::value)
      (assoc t ::value v)
      (update-in (assoc t ::value v) [::size] inc))
    (let [[k-first & k-rest] k,
          child (t k-first)]
      (if child
        (let [child' (trie-assoc child k-rest v)]
          (if (contains? child k-rest)
            (assoc t k-first child')
            (update-in (assoc t k-first child') [::size] inc)))
        (update-in (assoc t k-first (trie-assoc empty-trie k-rest v)) [::size] inc)))))

(defn trie-size
  [t]
  (::size t))

(defn trie-prefix-complete
  [t pre]
  (loop [pre (vec pre), node (get-in t pre)]
    (if (= 1 (count (dissoc t ::size)))
      (let [[[k v]] (dissoc t ::size)]
        (recur (conj pre k) v))
      pre)))

(defrecord ClojureRadixTree [t-ref]
  RadixTree
  (insert [_ k v]
    (if (trie-contains? @t-ref k)
      (throw (new DuplicateKeyException (str "Duplicate key: " k))))
    (swap! t-ref trie-assoc k v))
  ; I assume this method is supposed to return true if something was actually
  ; deleted. The documentation is not clear.
  (delete [_ k]
    (if (trie-contains? @t-ref k)
      (do (swap! t-ref trie-dissoc k) true)
      false))
  (find [_ k] (trie-get @t-ref k))
  (contains [_ k] (trie-contains? @t-ref k))
  (searchPrefix [_ pr recordLimit]
    (new ArrayList (take recordLimit (trie-prefix-get @t-ref pr))))
  (getSize [_]
    (trie-size @t-ref))
  (complete [_ prefix]
    (apply str
      (trie-prefix-complete @t-ref prefix))))

(defn create-empty-radix-tree
  []
  (new ClojureRadixTree (atom empty-trie)))
