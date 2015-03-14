(ns backend.users
  (:require [clojure.java.jdbc :as j]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"
   })

(defn reset-users! []
  []
  (j/db-do-commands db ()))

(reset-users!)

(defn create-users-schema
  []
  (j/create-table-ddl :users
                    [:password :text]
                    [:username :text]))

(defn user-exist [user]
  (let [query (format "select * from users where username='%s'" user)
        result (j/query db [query] :row-fn :cost)]
    (> (count result) 0)))

;TODO: what if we make a new database every single time??
(defn create-user [user password]
  (j/insert! db "users" {:username user :password password}))


(defn get-password [username]
  (let
      [querystring (format "select password from users where username='%s'" username)]
    (:password
     (first (j/query db
                     querystring)))))

(defn check-password [user password]
  (= (get-password user) password))

;TODO: should I define a protocol for a mock that behaves a bit like a databse?
