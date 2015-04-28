(ns hangman.secret-test
  (:require [hangman.secret :refer :all]
            [clojure.test :refer :all]))

(defn reset-game-fixture! [f]
  (reset-games!)
  (f))

(use-fixtures :each reset-game-fixture!)

(defn- length-current-games
  [] (count (current-games)))

(deftest uuid-test
  (testing "create new game"
    (new-game)
    (is (= 1 (length-current-games))))

  (testing "uuid-generation-is-string"-
    (is (pos? (count (uuid))))))

(deftest available-chars-test
  (testing "available chars shrinks"
    (let [game-id (new-game :secret "hello")]
      (is (= (count (available-letters game-id)) 26))
      (reveal-letter game-id \h)
      (is (= (count (available-letters game-id)) 25)))))

(deftest initialize-test
  (testing "new structure shape"
    (let [my-struct (initialize-struct "abc")
          desired {:attempts 0
                   :seen #{}
                   :struct [{:char \a :visible false} {:char \b :visible false} {:char \c :visible false}]}]
      (is (= my-struct desired))))

  (testing "Non chars are visible straight away"
    (let [with-hyphen (initialize-struct "abc'")
          string (secret-string (:struct with-hyphen))]
      (is (= string "___'")))))

(deftest updating-structure-test
  (testing "Updated existing structure"
    (let [my-struct (initialize-struct "abc")
          new-struct (update-struct my-struct \a true)]
      (is (true? (contains? (:seen new-struct) \a))))))

(deftest game-setter-and-getter-test
  (testing "create game with given string"
    (let [game-id (new-game :secret "secret")
          desired (clojure.string/join "" (repeat (count "secret") \_))]
      (is (= 1 (length-current-games)))
      (is (= desired (get-secret game-id))))))

(deftest secret-strings-test
  (let [sample-secret [{:char \x :visible false}
                       {:char \y :visible true}
                       {:char \z :visible false}]]

    (testing "mask and unmask"
      (is (= (secret-string sample-secret) "_y_")))))

(deftest secret-reveal-test
  (testing "reveal letter returns new string"
    (let [game-id (new-game :secret "secret")
          new-string (reveal-letter game-id \s)]
      (is (= "s_____" new-string))))

  (testing "reveal simple"
    (let [game-id (new-game :secret "xyz")]
      (is (= (reveal-letter game-id \x) "x__"))))

  (testing "found letter"
    (let [game-id (new-game :secret "secret")]
      (is (false? (found? game-id \y)))
      (is (true? (found? game-id \s)))))

  (testing "revealing add to seen letters"
    (let [game-id (new-game :secret "secret")]
      (reveal-letter game-id \s)
      (is (= "s_____" (get-secret game-id)))
      (is (true? (seen? game-id \s)))))

  (testing "reveal modifies attempts"
    (let [game-id (new-game :secret "secrets")]
      (reveal-letter game-id \s)
      (is (= 0 (attempts game-id)))
      (reveal-letter game-id \x)
      (is (= 1 (attempts game-id)))))

  (testing "reveal is case insensitive"
    (let [game-id (new-game :secret "Hello")
          secret-lower (reveal-letter game-id \h)]
      (is (= "H____" secret-lower)))))

(deftest game-status-test
  (testing "game over is over"
    (let [secret-struct [{:char \x :visible true}]]
      (is (true? (game-over secret-struct))))))
