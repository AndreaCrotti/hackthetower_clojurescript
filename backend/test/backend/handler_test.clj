(ns backend.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [backend.handler :refer :all]))


(deftest initialize-test
  (testing "Init everything"
    (let [response (app (mock/request :post "/initialize"))]
      (is (= (:status response) 201))))
  (testing "get current word"
    (let [response (app (mock/request :get "/status"))]
      (is (= (:status response) 200)))))

(deftest move-test
  (testing "set up and do a move"
   (initialize-word "abc")
    (let [response (app (mock/request :post "/move" {:letter \a}))]
      (is (= (:status response) 200))
      (is (= (:body response) "a__")))))
