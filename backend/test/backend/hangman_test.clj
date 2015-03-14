(ns backend.hangman-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [backend.hangman :refer :all]))

(def sample-dictionary
  "abc
sample
something
")

(deftest hangman-test
  (testing "Generation"
    (let [sample (gen-string (str/split-lines sample-dictionary) 3)]
      (is (= "abc" sample)))))
