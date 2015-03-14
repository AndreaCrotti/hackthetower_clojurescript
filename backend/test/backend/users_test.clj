(ns backend.users-test
  (:require [clojure.test :refer :all]
            [backend.users :refer :all]))

(deftest users-test
  (testing "check-pass"
    (create-user "user" "pass")
    (is (= true (check-password "user" "pass")))))
