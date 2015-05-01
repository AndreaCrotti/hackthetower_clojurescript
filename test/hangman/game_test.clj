(ns hangman.game-test
  (:require [hangman.game :refer :all]
            [clojure.test :refer :all]))


(deftest get-letter-test
  (testing "One letter should work fine"
    (is (= "a" ""))))
