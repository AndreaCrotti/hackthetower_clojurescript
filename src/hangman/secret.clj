(ns hangman.secret
  (:require [clojure
             [set :as set]
             [string :as str]]
            [hangman.wordgen :as wordgen]))

(defn reset-games!
  []
  (def live-games (ref {})))

(reset-games!)

;TODO: should this be in util somehow?
(defn lowercase-char
  [char]
  (-> (str char)
      .toLowerCase
      (nth 0)))

(defn filter-char
  [letter el]
  (let [to-find (lowercase-char letter)]
    (if (= (lowercase-char (:char el)) to-find)
      {:char (:char el) :visible true}
      el)))

(defn- snapshot
  "Get the last situation for the given game-id"
  [game-id]
  (first (get @live-games game-id)))

(defn secret-string
  "Join the secret string structure marking hidden chars as _"
  [secret]
  (str/join (map #(if (:visible %) (:char %) \_) secret)))

(defn secret-string-initial
  "Join the secret string structure marking hidden chars as _"
  [secret]
  (str/join (map :char secret)))

(defn get-secret
  "Return the secret string for the given game id"
  [game-id]
  (secret-string (:struct (snapshot game-id))))

(defn initial-secret
  "Return the secret string for the given game id"
  [game-id]
  (secret-string-initial (:struct (snapshot game-id))))

(defn set-secret
  "Set the secret for a given name"
  [game-id game-struct]
  (dosync
   (alter live-games
          (fn [d] (assoc d game-id (conj (get d game-id) game-struct))))))

(defn update-struct
  "Update the structure and return a new one"
  [game-struct letter found]
  {:struct (map (partial filter-char letter) (:struct game-struct))
   :seen (conj (:seen game-struct) letter)
   :attempts (if-not found (-> game-struct :attempts inc) (-> game-struct :attempts))})

(defn gen-uuid
  "Get a new random UUID that represents a given name"
  [] (str (java.util.UUID/randomUUID)))

(def all-chars
  "Simple list of all the chars"
  (map char (range (int \a) (inc (int \z)))))

(defn valid-char
  "Check if the char given is valid or not"
  [char]
  (contains? (set all-chars) char))

(defn available-letters
  [game-id]
  (set/difference (set all-chars) (:seen (snapshot game-id))))

;; (mapv (fn [i] {:char i :visible (not (valid-char i))}))
(defn initialize-struct
  "Initialize an empty strurcture with no seen letters and a given structure"
  [word]
  {:seen #{}
   :struct (mapv (fn [i] {:char i :visible (not (valid-char i))}) word)
   :attempts 0})

(defn attempts
  "Return number of attempts done in this game"
  [game-id]
  (-> game-id snapshot :attempts))

(defn found?
  [game-id letter]
  (let [current-struct (-> game-id snapshot :struct)
        founds (filter #(and (= letter (:char %)) (false? (:visible %))) current-struct)]
    (not (empty? founds))))

(defn seen?
  "Check if the given char is already seen or not"
  [game-id letter]
  (contains? (-> game-id snapshot :seen) letter))

(defn reveal-letter
  "Modify the given name id by changing a letter"
  [game-id letter]
  (let [current (snapshot game-id)
        is-found (found? game-id letter)
        new-struct (update-struct current letter is-found)]

    (set-secret game-id new-struct)
    (get-secret game-id)))

(def word-length (delay (+ (rand-int 9) 6)))

;TODO: should this have a ! since it has a side effect as well?
(defn new-game
  "Create a new game and store it in the ref"
  [& {:keys [secret]}]
  (let [new-game-id (gen-uuid)
         ; this if can be moved in the pattern matching above?
        new-secret (if (nil? secret) (wordgen/gen-string wordgen/all-words @word-length) secret)
        new-secret-struct (initialize-struct new-secret)]

    ; using a list for the secret structure so it can be extended easily
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
  (every? :visible (-> game-id snapshot :struct)))
