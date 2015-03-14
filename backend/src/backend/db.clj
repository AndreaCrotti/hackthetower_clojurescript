(ns backend.db
  (:require [clojure.java.jdbc :as j]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"
   })

(defn reset-users!
  []
  (j/db-do-commands db ())

(defn create-users-schema
  []
  (j/create-table-ddl :users
                    [:password :text]
                    [:username :text]))

(defn create-db []
  (try (j/db-do-commands db
                       (create-users-schema))
       (catch Exception e (println e))))


(defn get-password [username]
  (let
      [querystring (format "select password from users where username='%s'" username)]
    (:password
     (first (j/query db
                     querystring)))))

(create-db)
