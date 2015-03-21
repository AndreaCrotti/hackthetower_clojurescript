;TODO: add AOT thing to make it faster
(ns backend.game
  (:gen-class :main true)
  (:require [backend.hangman :refer :all]))

(def all-chars (map char (range (int \a) (inc (int \z)))))

(defn random-char
  []
  (pick-random-element all-chars))

;TODO: get an initial counter that can be passed around
;TODO: get word from entry
(defn loop
  []
  (if (game-over @masked-word)
    (println (format "Game finished, answer was %s" @secret-word))
    (do
      (move (random-char))
      (println (format "now word is %s" (secret-string @masked-word)))
      (loop))))

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (println args)
  (set-secret 10)
  )

