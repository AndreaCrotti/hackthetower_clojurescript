;TODO: add AOT thing to make it faster
(ns hangman.game
  (:gen-class :main true)
  (:require [hangman.hangman :refer :all])
  (:require [clojure.set :as set]))


(defn attempt-guess
  []
  (println "What is your guess?")
  (let [guess (read-line)]
    (guess-word guess)))

;TODO: try out some other strategies likes
(defn get-letter
  []
  (let [valid-chars (set/difference (set all-chars) @seen-letters)]
    (println
     (format "What letter you want to reveal? Possible choices = %s" (clojure.string/join " " valid-chars)))
    (let [user-input (read-line)]
      (if (> (count user-input) 1)
        (do
          (println "Input too log")
          (get-letter))
        (let [char (nth user-input 0)]
          (if (contains? valid-chars char)
            char
            (do
              (println "Not a valid choice")
              (get-letter))))))))


(defn game-loop
  [limit & {:keys [attempt] :or {attempt 0}}]
  (if (game-over @masked-word)
    (println "Congratulations, You won!")
    (if (= limit attempt)
      (println "Sorry no more attempts, the secret word was" @secret-word)
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

