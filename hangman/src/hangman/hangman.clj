(ns hangman.hangman
  (:require [clojure.string :as str]
            [hangman.secret :as secret]))

(def secret-word (ref ""))
(def masked-word (ref []))
(def seen-letters (ref #{}))


;TODO: need to unify all the side effects inside one containe
(defn move
  "Do one move and, return True if the letter was found or False otherwise"
  [letter]
  (dosync (alter seen-letters conj letter))
  ;; (swap! seen-letters conj letter)
  (let [changed (secret/found? letter @masked-word)]
    (when changed
      (dosync
       (ref-set masked-word (secret/reveal-letter @masked-word letter))))
    changed))
