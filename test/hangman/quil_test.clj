(ns hangman.quil-test
  (:require [hangman.quil :refer :all]
            [clojure.test :refer :all]))


(deftest quil-test
  (testing "up to steps filter correctly"
    (let [res (up-to-step 2)]
      (is (= (count res) 2))
      (is (= [0 1] (keys res)))))

  (testing "1 to many steps")

  (testing "box coordinates"
    ()))
