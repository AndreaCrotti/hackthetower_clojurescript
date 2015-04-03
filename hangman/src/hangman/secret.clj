(ns hangman.secret)

;TODO: is there a nicer way to do this?like defonce?
(defn reset-games
  []
  (def live-games (ref {})))

;TODO: should this be in util somehow?
(defn lowercase-char
  [char]
  ;TODO: this seems quite hacky can it be improved?
  (nth (seq (char-array (.toLowerCase (str char)))) 0))

;TODO: is there a way to print out the current variables in the given function?
(defn filter-char
  [letter el]
  (let [to-find (lowercase-char letter)]
    (if (= (lowercase-char (:char el)) to-find)
      ;TODO: is there a better way to modify this structure inline?
      {:char (:char el) :visible true}
      el)))

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
