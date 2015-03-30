(ns hangman.core-test
  (:require [clojure.test :refer :all]
            [hangman.core :refer :all]
            [hangman.users :refer [create-db! delete-db!]]))

(defn setup-db
  []
  )

(deftest registration
  (testing "register-or-login"
    (try
      (create-db!)
      (let [reg (register-or-login "new" "user")]
        (is (= (:message reg) "created")))
      (finally (delete-db!)))))


;TODO: implement authentication using JWT or similar mechanisms


