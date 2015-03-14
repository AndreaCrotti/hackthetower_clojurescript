(ns game.core
  (:require [clojure.browser.repl :as repl]))

;; (repl/connect "http://localhost:9000/repl")

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(def users (atom {}))

(defn add-user [x]
  (assoc @users x {})
  (println "Added user"))

(defn register [user password]
  "Register a user with user and password"
  )
