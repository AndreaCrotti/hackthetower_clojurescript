;TODO: add AOT thing to make it faster
(ns backend.game
  (:gen-class :main true)
  (:require [backend.hangman :refer :all]))


(defn move
  "Reveal one letter or try to guess immediately"
  [player]
  ()
  )

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (println args)
  (set-secret 10)
  )

