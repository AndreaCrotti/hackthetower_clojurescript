(ns backend.hangman
  (:require [clojure.string :as str]))

;; Generate a random string which has to be guessed by different users

(defn gen-string
  "Generate a random string"
  [dictionary n]
  (let [index (- (* n (Math/random)) 1)]
    (nth (filter #(= (count %) n) dictionary) index)))
