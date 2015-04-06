(ns hangman.hangman-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [hangman.hangman :refer :all]))


(defn- reset-all
  []
  (dosync
   (ref-set seen-letters #{})
   (ref-set secret-word "abc")
   (ref-set masked-word (-> @secret-word
                            initialize-struct
                            vec))))

(deftest hangman-game
  ; this test checks for side effects basically
  (testing "move changes available letters"
    (reset-all)
    (is (true? (move \a)))
    (is (= @seen-letters #{\a})))

  (testing "game over"
    (let [secret-struct [{:char \x :visible true}]]
      (is (true? (game-over secret-struct))))))
