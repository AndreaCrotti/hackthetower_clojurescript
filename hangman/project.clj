(defproject hangman "0.1.0-SNAPSHOT"
  :description "Project hangman for game playing"
  :url "http://example.cnom/FIXME"
  :main hangman.game
  ;TODO: check if the liecense is alright?
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [metosin/ring-swagger "0.19.1"]
                 [metosin/ring-swagger-ui "2.1.8-M1"]
                 [metosin/ring-http-response "0.6.1"]
                 [play-clj "0.4.5"]
                 [quil "2.2.5"]
                 [compojure "1.3.2"]
                 [environ "1.0.0"]
                 [slamhound "1.5.5"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.5"]
                 [ring/ring-defaults "0.1.2"]
                 ;TODO: add postgres and datomic
                 [org.xerial/sqlite-jdbc "3.8.7"]]
  
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]]
  :ring {:handler hangman.handler/app
         :auto-reload? true
         :auto-refresh? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [midje "1.6.3"]
                        [ring-mock "0.1.5"]]}})
