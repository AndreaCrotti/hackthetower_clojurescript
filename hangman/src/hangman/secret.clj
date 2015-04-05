(ns hangman.secret
  (:require [clojure.string :as str]
            [hangman.wordgen :as wordgen]))

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


(defn secret-string
  "Join the secret string structure marking hidden chars as _"
  [secret]
  (str/join (map #(if (:visible %) (:char %) \_) secret)))

(defn get-secret
  "Return the secret string for the given game id"
  [gameid]
  (secret-string (get @live-games gameid)))

(defn uuid
  "Get a new random UUID that represents a given name"
  [] (str (java.util.UUID/randomUUID)))

(def all-chars
  "Simple list of all the chars"
  (map char (range (int \a) (inc (int \z)))))

(defn valid-char
  [char]
  (contains? (set all-chars) char))

(defn initialize-struct
  [word]
  ;TODO: is using a vec really necesary?
  (vec
   (for [i word]
     {:char i :visible (not (valid-char i))})))

(defn new-game
  "Create a new game and store it in the ref"
  [& {:keys [secret]}]
  (let [new-game-id (uuid)
        new-secret (if (nil? secret) (wordgen/gen-string wordgen/all-words 10) secret)
        new-secret-struct (initialize-struct new-secret)]
    (dosync
     (alter live-games #(assoc % new-game-id new-secret)))
    new-game-id))


(defn current-games
  "Return a list of currently active games"
  [] (keys @live-games))
