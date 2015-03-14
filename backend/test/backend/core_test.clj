(ns backend.core-test
  (:require [clojure.test :refer :all]
            [backend.core :refer :all]))

(deftest a-test
  (testing "register-or-login"
    (let [reg (register-or-login "new" "user")]
      (is (= (:message reg) "created"))))
  (testing "Check password"
    (do
      (create-user "user" "pass")
      (is (= true (check-password "user" "pass"))))))
