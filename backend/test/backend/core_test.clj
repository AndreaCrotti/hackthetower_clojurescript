(ns backend.core-test
  (:require [clojure.test :refer :all]
            [backend.core :refer :all]
            [backend.users :refer [reset-users!]]))

(deftest registration
  (testing "register-or-login"
    (reset-users!)
    (let [reg (register-or-login "new" "user")]
      (is (= (:message reg) "created")))))
