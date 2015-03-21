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


(defn game-loop
  [limit & {:keys [attempt] :or {attempt 0}}]
  (if (game-over @masked-word)
    (println (format "Game finished, answer was %s" @secret-word))
    (if (= limit attempt)
      (println "Sorry no more attempts av")

      (do
        (move (get-letter))
        (println (format "At attempt %d now word is %s" attempt (secret-string @masked-word)))
        (if (attempt-guess)
          (println "Congratulations you got the right word")
          (game-loop limit :attempt (inc attempt)))))))

;TODO: for the parallel computation create one string for every game
(defn -main
  "Application main function"
  [& args]
  (set-secret 10)
  (game-loop 10 :attempt 0))

