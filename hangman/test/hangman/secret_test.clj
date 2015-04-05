(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))

(defn- length-current-games
  [] (count (current-games)))

(deftest uuid-test
  (testing "create new game"
    (reset-games)
    (new-game)
    (is (= 1 (length-current-games))))

  (testing "uuid-generation-is-string"
    (is (pos? (count (uuid))))))

(deftest game-setter-and-getter-test
  (testing "create game with given string"
    (reset-games)
    (let [gameid (new-game :secret "secret")
          desired (clojure.string/join "" (repeat (count "secret") \_))]
      (is (= 1 (length-current-games)))
      (is (= desired (get-secret gameid))))))

(deftest reveal-letter-test
  (testing "reveal letter returns new string"
    (reset-games)
    (let [gameid (new-game :secret "secret")
          new-string (reveal-letter gameid \s)]
      (is (= "s_____" new-string)))))
