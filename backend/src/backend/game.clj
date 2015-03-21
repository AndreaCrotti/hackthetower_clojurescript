;TODO: add AOT thing to make it faster
(ns backend.game
  (:gen-class :main true)
  (:require [backend.hangman :refer :all]))


;TODO: function to get the next move, could be polymorphic
;or just be passed in?

;TODO: get an initial counter that can be passed around
;TODO: get word from entry
(defn loop
  [{:keys [attempt] :or {attempt 0}}]
  (if (game-over @masked-word)
    (println (format "Game finished, answer was %s" @secret-word))
    (do
      (move (random-char))
      (println (format "At attempt %d now word is %s" attempt (secret-string @masked-word)))
      (loop (inc attempt)))))

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (set-secret 10)
  (loop 0))

