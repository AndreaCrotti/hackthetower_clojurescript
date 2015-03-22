(ns backend.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [backend.hangman :as hang]))

;; {:over false :current "___a__" :attempts-left 4}

(defn move-api [letter]
  "Move and return a JSON response to the client"
  
  )

(defn initialize-word []
  (hang/set-secret 10)
  {:status 201})

(defroutes app-routes
  (POST "/initialize" [] (initialize-word))
  (POST "/move" [letter] (println (format "got letter %s" letter)))
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
