(ns hangman.system
  (:require [com.stuartsierra.component :as component]
            [hangman
             [database :as database]
             [wordgen :as wordgen]
             [handler :as handler]]
            [clojure.tools.cli :refer [parse-opts]]))


(defn make-system [{:keys [is-dev? db-file port]}]
  (component/system-map
   :database (database/map->new-database {:db-file db-file})

   :wordgen (wordgen/map->new-wordgen)

   :webserver (component/using
               (handler/new-webserver {:ring {:port (or port 3000) :join? false}
                                       :is-dev? is-dev?}))))


                                        ;TODO: how do we make something optional?
(defn -main
  [& args]
  (let [options (parse-opts args cli-options)
        length (-> options :options :length)
        attempts (-> options :options :attempts)]

    (if (-> options :options :help)
      (clojure.pprint/pprint cli-options)
      (component/start (make-system {:is-dev? true
                                     :db-file "hello.sqlite"})))))

