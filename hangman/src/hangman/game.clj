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
  "Main loop of the game"
  [limit & {:keys [attempt] :or {attempt 0}}]
  (if (game-over @game-id)
    (println "Congratulations, You won!")
    (if (= limit (attempts @game-id))
      (println "Game over sorry")
      (reveal-letter @game-id (get-letter)))))

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
        length (-> options :options :length)
        current-game-id (new-game :secret (wordgen/gen-string wordgen/all-words length))]
    
    (reset! game-id current-game-id)
    (game-loop attempts :attempt 0)))
