(ns hangman.users
  (:require [clojure.java.jdbc :as j]
            [clojure.java.io :refer [delete-file]]))

(def db-filename "db/database.db")

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     db-filename
   })

(defn create-users-schema
  []
  (j/create-table-ddl :users
                    [:password :text]
                    [:username :text]))

(defn user-exist [user]
  (let [query (format "select * from users where username='%s'" user)
        result (j/query db [query] :row-fn :cost)]
    (pos? (count result))))

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

(defn create-db! []
  (try (j/db-do-commands db
                       (create-users-schema))
       (catch Exception e (println e))))


(defn delete-db!
  []
  (delete-file db-filename))
