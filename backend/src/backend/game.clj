;TODO: add AOT thing to make it faster
(ns backend.game
  (:gen-class :main true)
  (:require [backend.hangman :refer :all]))


(defn attempt-guess
  []
  (println "What is your guess?")
  (let [guess (read-line)]
    (guess-word guess)))

(defn get-letter
  []
  (println "What letter you want to reveal?")
  (nth (read-line) 0))


(defn loop
  [& {:keys [attempt] :or {attempt 0}}]
  (if (game-over @masked-word)
    (println (format "Game finished, answer was %s" @secret-word))
    (do
      ;; (move (random-char))
      (move (get-letter))
      (println (format "At attempt %d now word is %s" attempt (secret-string @masked-word)))
      (if (attempt-guess)
        (println "Congratulations you got the right word")
        (loop :attempt (inc attempt))))))

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (set-secret 10)
  (loop :attempt 0))

