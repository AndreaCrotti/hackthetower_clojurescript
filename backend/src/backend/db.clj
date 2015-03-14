(ns backend.db
  (:require [clojure.java.jdbc :refer :all]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/database.db"
   })

(defn reset-users
  []
  (db-do-commands db (drop-table-ddl :users)))

(defn create-users-schema
  []
  (create-table-ddl :users
                    [:password :text]
                    [:username :text]))

(defn create-db []
  (try (db-do-commands db
                       (create-users-schema))
       (catch Exception e (println e))))

(create-db)
