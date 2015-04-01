(ns hangman.secret)

;TODO: is there a nicer way to do this?like defonce?
(defn reset-games
  []
  (def live-games (ref {})))

;TODO: every value of the dictionary is simply a word structure
(defn reveal-letter
  "Return another secret structure where the revealed chars are marked now as visible"
  [secret letter]
  (map (partial filter-char letter) secret))

(reset-games)

(defn reveal-letter
  "Modify the given name id by changing a letter"
  [game-id letter]
  ())

(defn uuid
  "Get a new random UUID that represents a given name"
  [] (str (java.util.UUID/randomUUID)))


(defn new-game
  "Create a new game and store it in the ref"
  []
  (let [new-game-id (uuid)]
    (dosync
     (alter live-games #(assoc % new-game-id "")))))


(defn current-games
  "Return a list of currently active games"
  [] (keys @live-games))
