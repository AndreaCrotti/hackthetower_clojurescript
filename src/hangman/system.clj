(ns hangman.system
  (:gen-class
   :main true)
  (:require [com.stuartsierra.component :as component]
            [hangman
             [database :as database]
             [wordgen :as wordgen]
             [handler :as handler]]
            [clojure.tools.cli :refer [parse-opts]]))


(defn make-system [{:keys [is-dev? db-file port]}]
  (component/system-map
   :database (database/new-database db-file)

   :wordgen (wordgen/new-wordgen 10)

   :webserver (component/using
               (handler/new-webserver {:ring {:port (or port 3000) :join? false}
                                       :is-dev? is-dev?}))))

(def cli-options
  ;; An option with a required argument
  [["-n" "--attempts ATTEMPT" "Number of max attempts"
    :default 10
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]

   ["-l" "--length LENGTH" "Random string length"
    :default 10
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]

   ["-s" "--secret" "Optional string to set"]

   ["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])

(defn -main
  [& args]
  (let [options (parse-opts args cli-options)
        length (-> options :options :length)
        attempts (-> options :options :attempts)]

    (if (-> options :options :help)
      (clojure.pprint/pprint cli-options)
      (component/start (make-system {:is-dev? true
                                     :db-file "hello.sqlite"})))))

