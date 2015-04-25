(ns hangman.users-test
  (:require [clojure.test :refer :all]
            [hangman.users :refer :all]))


(defn reset-users-tables! [f]
  (create-users-schema)
  (f)
  (delete-db!))

(use-fixtures :each reset-users-tables!)

#_(deftest users-test
    (testing "check-pass"
      (create-user "user" "pass")
      (is (= true (check-password "user" "pass")))))
