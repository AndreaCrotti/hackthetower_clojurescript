(defproject hangman "0.1.0-SNAPSHOT"
  :description "Project hangman for game playing"
  :url "http://example.cnom/FIXME"  ;TODO: add correct url
  :main hangman.game
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/core.memoize "0.5.9"]
                 [org.clojure/tools.cli "0.3.5"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.typed "0.3.32"]

                 [prismatic/schema "1.1.6"]
                 [com.cemerick/friend "0.2.3"]
                 [clj-jwt "0.1.1"]

                 [quil "2.6.0"]
                 [inkwell "0.1.1"]

                 [expectations "2.1.9"]
                 [org.clojure/test.check "0.9.0"]

                 [http-kit "2.2.0"]

                 [hiccup "1.0.5"]
                 [clj-tagsoup "0.3.0" :exclusions [org.clojure/clojure]]

                 [org.xerial/sqlite-jdbc "3.18.0"]
                 [org.postgresql/postgresql "42.1.1"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [yesql "0.5.3"]

                 [metosin/ring-swagger "0.24.0"]
                 [metosin/ring-swagger-ui "2.2.10"]
                 [metosin/ring-http-response "0.9.0"]
                 [ring/ring-defaults "0.3.0"]
                 [ring-middleware-format "0.7.2" :exclusions [ring]]

                 [jarohen/phoenix "0.1.3"]
                 [com.stuartsierra/component "0.3.2"]

                 [compojure "1.6.0"]

                 [environ "1.1.0"]
                 [slamhound "1.5.5"]]
  
  :phoenix/config "hangman.edn"

  :plugins [[lein-ring "0.8.13"]
            [lein-expectations "0.0.8"]
            [lein-environ "1.0.0"]]

  :ring {:handler hangman.handler/app
         :auto-reload? true
         :auto-refresh? true}
  ;; :aliases {"test" ["expectations"]}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
