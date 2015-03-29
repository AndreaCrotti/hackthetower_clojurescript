(defproject backend "0.1.0-SNAPSHOT"
  :description "Project backend for game playing"
  :url "http://example.com/FIXME"
  :main backend.game
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [metosin/ring-swagger "0.19.1"]
                 [play-clj "0.4.5"]
                 [compojure "1.3.2"]
                 [environ "1.0.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.5"]
                 [ring/ring-defaults "0.1.2"]
                 [org.xerial/sqlite-jdbc "3.8.7"]]
  
  :plugins [[lein-ring "0.8.13"]
            [lein-environ "1.0.0"]]
  :ring {:handler backend.handler/app
         :auto-reload? true
         :auto-refresh? true}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
