(ns hangman.users-test
  (:require [clojure.test :refer :all]
            [hangman.users :refer :all]))

#_(deftest users-test
  (testing "check-pass"
    (create-user "user" "pass")
    (is (= true (check-password "user" "pass")))))
