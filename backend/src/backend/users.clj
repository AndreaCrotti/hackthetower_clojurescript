(ns backend.users
  (:require [backend.db :refer :all]))

(def users (atom {}))

(defn reset-users! []
  (def users (atom {})))

(reset-users!)

(defn user-exist [user]
  (@users user))

(defn create-user [user password]
  (swap! users
         (fn [m] (assoc m user {:password password}))))


(defn check-password [user password]
  (= (:password (@users user)) password))
