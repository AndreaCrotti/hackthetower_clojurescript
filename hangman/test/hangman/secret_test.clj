(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))


(defn uuid
  "Ge"
  [] (str (java.util.UUID/randomUUID)))
