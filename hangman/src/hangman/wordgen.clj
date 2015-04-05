(ns hangman.wordgen
  (:require [clojure.string :as str]
            [hangman.utils :as utils]))

;TODO: this could be more configurable

(def dictionary-file "/usr/share/dict/british")
(def all-words (str/split-lines (slurp dictionary-file)))

(defn gen-string
  "Generate a random string"
  [dictionary n]
  (utils/pick-random-element (filter #(= (count %) n) dictionary)))
