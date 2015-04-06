(ns hangman.utils-test
  (:require [hangman.utils :refer :all]
            [clojure.test :refer :all]))


(deftest random-element-test
  (let [sample ["abc" "bde"]]
    (testing "Random element is part of the original list given"
      (is (true? (contains? (set sample) (pick-random-element sample)))))))
