(ns backend.hangman
  (:require [clojure.string :as str]))

(def secret-word (atom ""))
(def dictionary-file "/usr/share/dict/british")
(def all-words (str/split-lines (slurp dictionary-file)))
;; Generate a random string which has to be guessed by different users

(defn guess-word
  [word]
  (= word @secret-word))

(defn pick-random-element
  [coll]
  (let [size (count coll)
        index (Math/round (* (Math/random) (dec size)))]
    (nth coll index)))

(defn gen-string
  "Generate a random string"
  [dictionary n]
  (pick-random-element (filter #(= (count %) n) dictionary)))

(defn secret-string
  [secret]
  (str/join (map #(if (:visible %) (:char %) \_) secret)))

(defn reveal-letter
  [secret letter]
  (map #(if (= (:char %) letter) {:char letter :visible true} %) secret))
