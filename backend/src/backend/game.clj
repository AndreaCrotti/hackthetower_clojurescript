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
  ;TODO: add here a way to get a list of valid choices
  (println "What letter you want to reveal?")
  (nth (read-line) 0))


(defn game-loop
  [limit & {:keys [attempt] :or {attempt 0}}]
  (if (game-over @masked-word)
    (println "Congratulations, You won!")
    (if (= limit attempt)
      (println ("Sorry no more attempts, the secret word was %s" @secret-word))

      (do
        (let [new-attempt (if (not (move (get-letter))) (inc attempt) attempt)]
          (println (format "At attempt %d now word is %s" new-attempt (secret-string @masked-word)))
          (game-loop limit :attempt new-attempt))))))

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (set-secret 10)
  (game-loop 10 :attempt 0))

