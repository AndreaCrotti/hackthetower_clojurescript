(ns hangman.database
  (:require [com.stuartsierra.component :as component]
            [clojure.java
             [io :refer [delete-file]]
             [jdbc :as j]]))

(defn db-conn [db-file]
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     db-file
   })

(defrecord Database [db-file]
  component/Lifecycle
  (start [component]
    (println "Creating database at file " db-file)
    (assoc component :db-conn (db-conn db-file)))

  (stop [component]
    (println "Stopping the database stored in file " (:subname (:db-conn component)))
    (dissoc component :db-conn)))

(defn new-database [db-file]
  "Constructor that will generate an empty database"
  (map->Database {:db-file db-file}))
