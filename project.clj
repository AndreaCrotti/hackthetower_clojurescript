(defproject hangman "0.1.0-SNAPSHOT"
  :description "Project hangman for game playing"
  :url "http://example.cnom/FIXME"  ;TODO: add correct url
  :main hangman.game
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/core.memoize "0.5.7"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]


                 [prismatic/schema "0.4.3"]
                 [com.cemerick/friend "0.2.1"]
                 [clj-jwt "0.0.13"]

                 [play-clj "0.4.6"]
                 [quil "2.2.6"]
                 [inkwell "0.1.1"]

                 [expectations "2.1.1"]

                 [http-kit "2.1.19"]

                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]

                 [org.xerial/sqlite-jdbc "3.8.10.1"]
                 [org.clojure/java.jdbc "0.3.7"]

                 [metosin/ring-swagger "0.20.4"]
                 [metosin/ring-swagger-ui "2.1.8-M1"]
                 [metosin/ring-http-response "0.6.2"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-middleware-format "0.5.0" :exclusions [ring]]

                 [jarohen/phoenix "0.1.2"]
                 [com.stuartsierra/component "0.2.3"]

                 [compojure "1.3.4"]
                 [bidi "1.19.0"]

                 [environ "1.0.0"]
                 [slamhound "1.5.5"]]
  
  :plugins [[lein-ring "0.8.13"]
            [lein-expectations "0.0.8"]
            [lein-environ "1.0.0"]]

  :ring {:handler hangman.handler/app
         :auto-reload? true
         :auto-refresh? true}
  ;; :aliases {"test" ["expectations"]}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [midje "1.6.3"]
                        [ring-mock "0.1.5"]]}})
