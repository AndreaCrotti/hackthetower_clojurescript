(ns game.core
  (:require [clojure.browser.repl :as repl]))

;; (repl/connect "http://localhost:9000/repl")

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(def users (atom {}))

(defn check-password [user password]
  (= (:password (@users user))))

(defn create-user [user password]
  (swap! users
         (fn [m] (assoc m user {:password password}))))

(defn register [user password]
  (if (@users user)
    (check-password user password)
    (create-user user password)))
                    
