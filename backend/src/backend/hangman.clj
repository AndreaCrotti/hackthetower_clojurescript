(ns backend.hangman
  (:require [clojure.string :as str]))

;TODO: should this be made private instead?
(def secret-word (atom ""))
(def masked-word (atom ""))
(def dictionary-file "/usr/share/dict/british")
(def all-words (str/split-lines (slurp dictionary-file)))
;; Generate a random string which has to be guessed by different users

(defn guess-word
  [word]
  (= word @secret-word))

(defn pick-random-element
  "Pick a random element from a collection"
  [coll]
  (let [size (count coll)
        index (Math/round (* (Math/random) (dec size)))]
    (nth coll index)))

(defn gen-string
  "Generate a random string"
  [dictionary n]
  (pick-random-element (filter #(= (count %) n) dictionary)))

(defn secret-string
  "Join the secret string structure marking hidden chars as _"
  [secret]
  (str/join (map #(if (:visible %) (:char %) \_) secret)))


(defn lowercase-char
  [char]
  ;TODO: this seems quite hacky can it be improved?
  (nth (seq (char-array (.toLowerCase (.toString char)))) 0))

(defn filter-char
  [letter el]
  (let [to-find (lowercase-char letter)]
    (if (= (lowercase-char (:char el)) to-find)
      {:char (:char el) :visible true}
      el)))

(defn reveal-letter
  "Return another secret structure where the revealed chars are marked now as visible"
  [secret letter]
  (map (partial filter-char letter) secret))
