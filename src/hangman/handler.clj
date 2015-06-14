(ns hangman.handler
  (:require [compojure
             [core :refer :all]
             [route :as route]]
            [com.stuartsierra.component :as component]
            [hangman.secret :as secret]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]))


(defrecord WebServer [port]
  component/Lifecycle
  (start [component]
    (println "starting the web server"))
  (stop [component]
    (println "Stopping the web server component")))

(defn new-webserver [port]
  (map->WebServer port))

(defn move-api
  [params]
  "Move and return a JSON response to the client"
  (let [game-id (:game-id params)
        letter (nth (:letter params) 0)]
    {:status 200 :body (secret/reveal-letter game-id letter)}))

(defn initialize-word
  "Initiaiize a random word"
  ([]
   {:status 201 :body (secret/new-game)})
  ([secret]
   {:status 201 :body (secret/new-game :secret secret)}))

(defroutes app-routes
  (GET "/" [] "No need to restart the server every time")
  (GET "/status" [game-id] {:status 200 :body (secret/get-secret game-id)})
  (POST "/initialize" [] (initialize-word))
  (POST "/move" params  (move-api (:params params)))
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
