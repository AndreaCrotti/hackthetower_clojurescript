(ns backend.hangman-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [backend.hangman :refer :all]))

(def sample-dictionary
  "abc
  sample
  something
")


(def sample-secret
  [{:char \x :visible false}
   {:char \y :visible true}
   {:char \z :visible false}])


(deftest hangman-test
  (testing "Guess string"
    (reset! secret-word "abc")
    (true? (guess-word "abc")))

  (testing "Random element"
    ;; (doseq [i (range 10)]
    (pick-random-element ["abc" "bde"]))

  (testing "Mask"
    (is (= (secret-string sample-secret) "_y_")))

  (testing "Non chars are visible straight away"
    (let [with-hyphen (initialize-struct "abc'")
          string (secret-string with-hyphen)]
      (is (= string "___'"))))
  
  (testing "Generation"
    (let [sample (gen-string (str/split-lines sample-dictionary) 3)]
      (is (= "abc" sample)))))

(deftest hangman-reveal
  (testing "reveal simple"
    (is (= (secret-string (reveal-letter sample-secret \x)) "xy_")))

  (testing "reveal is case insensitive"
    (let [with-uppercase [{:char \X :visible false}]
          secret-lower (secret-string (reveal-letter with-uppercase \x))]
      (is (= "X" secret-lower)))))


(deftest hangman-game
  (testing "game over"
    (let [secret-struct [{:char \x :visible true}]]
      (is (true? (game-over secret-struct))))))
