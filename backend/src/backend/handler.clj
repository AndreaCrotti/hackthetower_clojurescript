(ns backend.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [backend.hangman :as hang]))

;; {:over false :current "___a__" :attempts-left 4}

(defn move-api [letter]
  "Move and return a JSON response to the client"
  (println "Found letter? " letter " in word " @hang/secret-word (hang/found? letter @hang/masked-word))
  ;; (println (str "Secret before " @hang/masked-word))
  (hang/move letter)
  ;; (println (str "Secret after " @hang/masked-word))
  {:status 200 :body (hang/secret-string @hang/masked-word)})

(defn initialize-word
  "Initiaiize a random word"
  ([]
   (do
     (hang/set-secret 10)
     {:status 201}))
  ([secret]
   (do
     (println "setting word to be " secret)
     (reset! hang/secret-word secret)
     (reset! hang/masked-word (hang/initialize-struct secret))
     {:status 201})))

(defroutes app-routes
  (POST "/initialize" [] (initialize-word))
  (POST "/move" params  (move-api (:letter (:params params))));; (println (format "got letter %s" letter)))
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
