(ns backend.core
  (:require [backend.users :refer :all]))

(def OK "ok")
(def ERROR "error")

;TODO: pass a map to make it more easy to test

(defn register-or-login [user password]
  "Return a tuple with the status and the error message if wrong"
  (if (user-exist user)
    (if (check-password user password)
      {:result OK :message "logged-in"}
      {:result ERROR :message "wrong-password"})
    (do
      (create-user user password)
      {:result OK :message "created"})))
