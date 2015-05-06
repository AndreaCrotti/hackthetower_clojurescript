(ns hangman.wordgen-test
  (:require [hangman.wordgen :refer :all]
            [clojure.test :refer :all]
            [clojure.string :as str]))


(def sample-dictionary
  "abc
  sample
  something
")


(deftest generation-test
  (testing "Generation given dictionary"
    (let [sample (gen-string (str/split-lines sample-dictionary) 3)]
      (is (= "abc" sample))))

  (testing "Right length"
    (let [sample (gen-string all-words 3)]
      (is (= 3 (count sample))))))

(deftest suggestion-test
  (testing "Suggest a definition"
    ))
