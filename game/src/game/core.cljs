(ns game.core
  (:require [clojure.browser.repl :as repl]
            [dommy.core :refer-macros [sel sel1]]))

;; (repl/connect "http://localhost:9000/repl")

(enable-console-print!)

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(def users (atom {}))

(defn check-password [user password]
  (= (:password (@users user))) password)

(defn create-user [user password]
  (swap! users
         (fn [m] (assoc m user {:password password}))))

(defn register-or-login [user password]
  (if (@users user)
    (if (check-password user password)
      (do
        (println "Welcome again"))
      (println "Wrong password sorry"))
    (create-user user password)))
                    
(defn print-input []
  (println (.-value (sel1 "#user"))))

(defn get-username []
  (.-value (sel1 "#user")))

(defn get-password []
  (.-value (sel1 "#password")))

(defn submit []
  (register-or-login (get-username) (get-password)))
