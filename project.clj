(defproject hangman "0.1.0-SNAPSHOT"
  :description "Project hangman for game playing"
  :url "http://example.cnom/FIXME"  ;TODO: add correct url
  :main hangman.game
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"] ;TODO: try clojure 1.7 and add core.async
                 [com.cemerick/friend "0.2.1"]
                 [clj-jwt "0.0.13"]
                 [http-kit "2.1.19"]
                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]
                 [org.clojure/java.jdbc "0.3.7"]
                 [metosin/ring-swagger "0.20.4"]
                 [metosin/ring-swagger-ui "2.1.8-M1"]
                 [metosin/ring-http-response "0.6.2"]
                 [jarohen/phoenix "0.1.2"]
                 [com.stuartsierra/component "0.2.3"]
                 [play-clj "0.4.6"]
                 [compojure "1.3.4"]
                 [bidi "1.19.0"]
                 [environ "1.0.0"]
                 [org.clojure/core.memoize "0.5.7"]
                 [slamhound "1.5.5"]
                 [ring-middleware-format "0.5.0" :exclusions [ring]]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-defaults "0.1.5"]
                 ;TODO: add postgres and datomic
                 [org.xerial/sqlite-jdbc "3.8.10.1"]]
  
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]]
  :ring {:handler hangman.handler/app
         :auto-reload? true
         :auto-refresh? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [midje "1.6.3"]
                        [ring-mock "0.1.5"]]}})
