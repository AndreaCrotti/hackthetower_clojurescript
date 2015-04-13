(defproject hangman "0.1.0-SNAPSHOT"
  :description "Project hangman for game playing"
  :url "http://example.cnom/FIXME"
  :main hangman.game
  ;TODO: check if the liecense is alright?
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  ;TODO: organize a bit better the dependencies
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-jwt "0.0.13"]
                 [http-kit "2.1.16"]
                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]
                 [org.clojure/java.jdbc "0.3.6"]
                 [metosin/ring-swagger "0.19.4"]
                 [metosin/ring-swagger-ui "2.1.8-M1"]
                 [metosin/ring-http-response "0.6.1"]
                 [jarohen/phoenix "0.1.1"]
                 [com.stuartsierra/component "0.2.3"]
                 [play-clj "0.4.6"]
                 [compojure "1.3.3"]
                 [environ "1.0.0"]
                 [slamhound "1.5.5"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-defaults "0.1.4"]
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
