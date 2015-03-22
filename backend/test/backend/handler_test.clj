(ns backend.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [backend.handler :refer :all]))


(deftest initialize
  (testing "Init everything"
    (let [response (app (mock/request :post "/initialize"))]
      (is (= (:status response) 201)))))
