(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))


(deftest uuid-test
  (testing "create new game"
    (reset-games)
    (new-game)
    (is (= 1 (count (current-games)))))

  (testing "uuid-generation-is-string"
    (is (pos? (count (uuid))))))
