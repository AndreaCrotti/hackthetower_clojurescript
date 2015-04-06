(ns hangman.hangman-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [hangman.hangman :refer :all]))


(deftest hangman-game
  ; this test checks for side effects basically
  (testing "move changes available letters"
    ;; (reset-all)
    (is (true? (move \a)))
    (is (= @seen-letters #{\a}))))
