(ns backend.core)

(def OK "ok")
(def ERROR "error")

;TODO: add support for more persistent data storage
(def users (atom {}))

;TODO: pass a map to make it more easy to test
(defn check-password [user password]
  (= (:password (@users user)) password))

(defn create-user [user password]
  (swap! users
         (fn [m] (assoc m user {:password password}))))


(defn register-or-login [user password]
  "Return a tuple with the status and the error message if wrong"
  (if (@users user)
    (if (check-password user password)
      {:result OK :message "logged-in"}
      {:result ERROR :message "wrong-password"})
    (do
      (create-user user password)
      {:result OK :message "created"})))
