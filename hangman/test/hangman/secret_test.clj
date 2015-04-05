(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))

(def sample-secret
  [{:char \x :visible false}
   {:char \y :visible true}
   {:char \z :visible false}])


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

(deftest secret-strings-test
  (testing "mask and unmask"
    (is (= (secret-string sample-secret) "_y_"))))

(deftest secret-reveal-test
  (let [game-id (new-game :secret "xyz")]
    (testing "reveal letter returns new string"
      (reset-games)
      (let [gameid (new-game :secret "secret")
            new-string (reveal-letter gameid \s)]
        (is (= "s_____" new-string))))

    (testing "reveal simple"
      (is (= (secret-string (reveal-letter game-id \x)) "xy_")))

    (testing "found letter"
      (is (false? (found? \y [{:char \x :visible false}])))
      (is (true? (found? \x [{:char \x :visible false}]))))
    
    (testing "reveal is case insensitive"
      (let [with-uppercase [{:char \X :visible false}]
            secret-lower (secret-string (reveal-letter with-uppercase \x))]
        (is (= "X" secret-lower))))))
