(ns hangman.utils)

;TODO: order matters so be careful to leave things as they should be
(defn pick-random-element
  "Pick a random element from a collection"
  [coll]
  (let [size (count coll)
        index (Math/round (* (Math/random) (dec size)))]
    (nth coll index)))
