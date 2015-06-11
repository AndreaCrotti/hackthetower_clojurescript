(ns hangman.quil
  (:require [quil.core :as q]))


(defn setup []
  (q/smooth)
  (q/frame-rate 1)
  (q/background 200))


(defn draw []
  (q/line 0 0 10 10))


(q/defsketch hangman
  :title "Hangman game"
  :setup setup
  :draw draw
  :size [640 480])
