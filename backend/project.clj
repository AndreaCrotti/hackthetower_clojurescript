(defproject backend "0.1.0-SNAPSHOT"
  :description "Project backend for game playing"
  :url "http://example.com/FIXME"
  :main backend.game
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [play-clj "0.4.5"]
                 [compojure "1.3.2"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.xerial/sqlite-jdbc "3.8.7"]])
