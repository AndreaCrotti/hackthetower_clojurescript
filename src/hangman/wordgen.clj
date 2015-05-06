(ns hangman.wordgen
  (:require [clojure.string :as str]
            [pl.danieljanus.tagsoup :as tagsoup]
            [org.httpkit.client :as http]
            [hangman.utils :as utils]))

;; Paths to the wordreference content for the given word
;; #source-luna > div:nth-child(1) > section > div > div.def-list > section:nth-child(1) > div:nth-child(2) > di
;; //*[@id="source-luna"]/div[1]/section/div/div[1]/section[1]/div[1]/div

(defn search-word
  "Return the definition for the given word"
  [word]
  (let [url (format "http://dictionary.reference.com/browse/%s" word)
        response (tagsoup/parse url)]
    ))

;TODO: this could be more configurable

(def dictionary-file "src/hangman/british")
(def all-words (str/split-lines (slurp dictionary-file)))

(defn gen-string
  "Generate a random string"
  [dictionary n]
  (utils/pick-random-element (filter #(= (count %) n) dictionary)))


(defn suggest
  "Suggest a possible definition for the given word"
  [word]
  
  )
