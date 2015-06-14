(ns hangman.users
  (:require [com.stuartsierra.component :as component]
            [clojure.java
             [io :refer [delete-file]]
             [jdbc :as j]]))

(defrecord Db [db-file]
  component/Lifecycle
  (start [component]
    (println "Starting database")
    (assoc component :key "value"))

  (stop [component]
    (println "Stopping the database" (:key component))
    (assoc component :key nil)))

(defn new-database [db-file]
  "Constructor that will generate an empty database"
  (map->Db {:db-file db-file}))
