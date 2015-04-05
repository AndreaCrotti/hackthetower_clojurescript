(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))


(deftest uuid-test
  ;; (testing "create new game"
  ;;   (reset-games)
  ;;   (new-game)
  ;;   (is (= 1 (count (current-games)))))

  (testing "uuid-generation-is-string"
    (is (pos? (count (uuid))))))

(deftest game-setter-and-getter-test
  (testing "create game with given string"
    (reset-games)
    (let [gameid (new-game :secret "secret")
          desired (clojure.string/join "" (repeat (count "secret") \_))]
      (is (= 1 (count (current-games))))
      (is (= desired (get-secret gameid))))))
