;TODO: add AOT thing to make it faster
(ns hangman.game
  (:gen-class :main true)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [hangman.secret :refer :all]
            [hangman.wordgen :as wordgen]
            [clojure.set :as set]))

(def game-id (atom nil))



;TODO: add logging and other things
(defn get-letter
  "Get a letter that is not already used"
  []
  (let [valid-chars (available-letters @game-id)]
    (println "What letter you want to reveal? Possible choices = " (clojure.string/join " " valid-chars))
    (let [user-input (read-line)]
      ;both entries of zero and above 1 now give errors
      (if (or (> (count user-input) 1) (< (count user-input) 1))
         (do
           (println "Invalid input")
           (get-letter))
             (let [char (nth user-input 0)]
                (if (contains? valid-chars char)
                 char
                   (do
                     (println "Not a valid choice")
                     (get-letter))))))))




(defn game-loop
  "Main loop of the game"
  [limit]
  (if (game-over @game-id)
    (println "Congratulations, You won!")
    (if (= limit (attempts @game-id))
      ;revealed answer after game loss
      (println "Game over sorry, the word was" (initial-secret @game-id))
      (do
        (let [new-secret (reveal-letter @game-id (get-letter))]
          (println "at attempt " (attempts @game-id) " the secret is " new-secret)
          (game-loop limit))))))
