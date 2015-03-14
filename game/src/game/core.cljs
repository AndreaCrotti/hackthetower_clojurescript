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

(defn register [user password]
  (if (@users user)
    (check-password user password)
    (create-user user password)))
                    
(defn print-input []
  (println (.value (sell "#user"))))
  ;; (println (.value (dom/getElement "user"))))
  ;; (println (.value (.getElementById js/document "user"))))

(println "Recompiled")
