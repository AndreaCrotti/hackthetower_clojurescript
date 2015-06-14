(ns hangman.quil
  (:require [quil.core :as q]))

(def WIDTH 640)
(def HEIGHT 480)

(def showing-order
  {0 {:base (fn [] (q/box 10))}
   1 {:stick (fn [] (q/line HEIGHT (/ WIDTH 2)))}
   2 {:hook (fn [] (q/line 1 2))}})

(defn up-to-step
  "Return the variables up to the given step"
  [n]
  (->> showing-order
       (filter #(< (nth % 0) n))
       (into {})))


(defn setup []
  (q/smooth)
  (q/frame-rate 1)
  (q/background 200))


(defn draw
  []
  (q/ellipse 100 100 100 100)
  (q/line 0 0 10 10))


(q/defsketch hangman
  :title "Hangman game"
  :setup setup
  :draw draw
  :size [640 480])
