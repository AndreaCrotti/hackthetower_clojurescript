;TODO: add AOT thing to make it faster
(ns hangman.game
  (:gen-class :main true)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [hangman.hangman :refer :all]
            [clojure.set :as set]))


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

(def cli-options
  ;; An option with a required argument
  [["-n" "--attempts ATTEMPT" "Number of max attempts"
    :default 10
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]
   ["-l" "--length LENGTH" "Random string length"
    :default 10
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]
   ["-s" "--secret" "Optional string to set"]
   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])


(defn -main
  "Application main function"
  [& args]
  (let [options (parse-opts args cli-options)
        length (:length (:options options))
        attempts (:attempt (:options options))]
    (set-secret length)
    (game-loop attempts :attempt 0)))

