(defproject game "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/dommy "1.0.0"]
                 ;; [weasel "0.6.0"]
                 ;; [com.cemerick/piggieback "0.1.5"]
                 [org.clojure/clojurescript  "0.0-3058"]]

  :node-dependencies [[source-map-support "0.2.8"]]
  ;; :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-npm "0.4.0"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["out" "out-adv"]
2
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :compiler {
                :main game.core
                :output-to "out/game.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}
             {:id "release"
              :source-paths ["src"]
              :compiler {
                :main game.core
                :output-to "out-adv/game.min.js"
                :output-dir "out-adv"
                :optimizations :advanced
                :pretty-print true}}]})
