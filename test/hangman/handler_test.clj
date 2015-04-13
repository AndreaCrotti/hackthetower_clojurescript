(ns hangman.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [hangman.handler :refer :all]))


(deftest initialize-test
  (testing "Init everything"
    (let [response (app (mock/request :post "/initialize"))]
      (is (= (:status response) 201))))
  (testing "get current word"
    (let [response (app (mock/request :get "/status"))]
      (is (= (:status response) 200)))))

(deftest move-test
  (testing "set up and do a move"
    ;TODO: should this be done as call as well??
    (let [game-id (:body (initialize-word "abc"))
          response (app (mock/request :post "/move" {:letter \a :game-id game-id}))]

      (is (= (:status response) 200))
      (is (= (:body response) "a__")))))
