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

(reset-games)

(defn secret-string
  "Join the secret string structure marking hidden chars as _"
  [secret]
  (str/join (map #(if (:visible %) (:char %) \_) secret)))

;TODO: get and set might instead be stacks, and always taking the last
;element in the list, so we are not losing any information
(defn get-secret
  "Return the secret string for the given game id"
  [game-id]
  (secret-string (get @live-games game-id)))

(defn set-secret
  "Set the secret for a given name"
  [game-id secret-struct]
  (dosync
     (alter live-games (fn [d] (assoc d game-id secret-struct)))))

(defn reveal-letter
  "Modify the given name id by changing a letter"
  [game-id letter]
  (let [current (get @live-games game-id)
        new-struct (map (partial filter-char letter) current)]
    (set-secret game-id new-struct)
    (get-secret game-id)))


(defn uuid
  "Get a new random UUID that represents a given name"
  [] (str (java.util.UUID/randomUUID)))

(def all-chars
  "Simple list of all the chars"
  (map char (range (int \a) (inc (int \z)))))

(defn valid-char
  "Check if the char given is valid or not"
  [char]
  (contains? (set all-chars) char))

(defn initialize-struct
  [word]
  ;TODO: is using a vec really necesary?
  (vec
   (for [i word]
     {:char i :visible (not (valid-char i))})))

(defn found?
  [game-id letter]
  (let [current-struct (get @live-games game-id)
        founds (filter #(and (= letter (:char %)) (false? (:visible %))) current-struct)]
    (not (empty? founds))))

;TODO: should this have a ! since it has a side effect as well?
(defn new-game
  "Create a new game and store it in the ref"
  [& {:keys [secret]}]
  (let [new-game-id (uuid)
        new-secret (if (nil? secret) (wordgen/gen-string wordgen/all-words 10) secret)
        new-secret-struct (initialize-struct new-secret)]
    (set-secret new-game-id new-secret-struct)
    new-game-id))


(defn current-games
  "Return a list of currently active games"
  [] (keys @live-games))


(defn game-over
  "Return true if the given game is over"
  [game-id]
  ;TODO: find a way to avoid the nil poisoining
  ;this would evaluate to true if the game given is nil
  (every? :visible (get-secret game-id)))
