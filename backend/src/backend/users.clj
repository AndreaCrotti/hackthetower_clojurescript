(ns backend.users
  (:require [backend.db :as bdb]
            [clojure.java.jdbc :as j]))

(def USE-DB true)

(def users (atom {}))

(defn reset-users! []
  (if USE-DB
    (bdb/reset-users!)
    (def users (atom {}))))

(reset-users!)

(defn user-exist [user]
  (if USE-DB
    (let [query (format "select * from users where username='%s'" user)
          result (j/query bdb/db [query] :row-fn :cost)]
      (> (count result) 0))
    (@users user)))

;TODO: what if we make a new database every single time??
(defn create-user [user password]
  (if USE-DB
    (j/insert! bdb/db "users" {:username user :password password})
    (swap! users
           (fn [m] (assoc m user {:password password})))))

(format "select password from users where username='%s'" user)


(defn check-password [user password]
  (if USE-DB
    (= (bdb/get-password user) password)
    (= (:password (@users user)) password)))

;TODO: should I define a protocol for a mock that behaves a bit like a databse?
