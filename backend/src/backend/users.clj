(ns backend.users
  (:require [backend.db :as bdb]
            [clojure.java.jdbc :as j]))

(def users (atom {}))

(defn reset-users! []
  (bdb/reset-users!))

(reset-users!)

(defn user-exist [user]
  (let [query (format "select * from users where username='%s'" user)
        result (j/query bdb/db [query] :row-fn :cost)]
    (> (count result) 0)))

;TODO: what if we make a new database every single time??
(defn create-user [user password]
  (j/insert! bdb/db "users" {:username user :password password}))

(defn check-password [user password]
  (= (bdb/get-password user) password))

;TODO: should I define a protocol for a mock that behaves a bit like a databse?
