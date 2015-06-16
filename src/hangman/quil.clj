(ns hangman.quil
  (:require [quil.core :as q]))

(def steps
  "Number of steps to execute"
  (atom 7))

(defn increase-step
  "Add one more step, TODO: use channels instead of this maybe"
  []
  (swap! steps inc))

(def WIDTH 640)
(def HEIGHT 480)

(def BACKGROUND-COLOR 100)
(def LINES-COLOR 256)

(def sizes
  {:base 100
   :stick 50
   :hook 50})

(def dimensions
  (let [center (/ WIDTH 2)
        box-width 100
        box-height 100
        stick-length (+ HEIGHT 200)]
    {:base {:x (- center (/ box-width 2))
            :y (- HEIGHT box-height)
            :width box-width
            :height box-height}
     :stick {:x1 center :y1 HEIGHT
             :x2 center :y2 stick-length}}))

(def showing-order
  {0 {:base
      (fn [] (apply q/rect (vals (:base dimensions))))}

   1 {:stick
      (fn []
        (apply q/line (vals (:stick dimensions))))}

   2 {:hook (fn [] (q/line 100 100 200 500))}})

(defn up-to-step
  "Return the variables up to the given step"
  [n]
  (->> showing-order
       (filter #(< (nth % 0) n))
       (into {})))

(defn flatten-functions
  []
  (let [submap (up-to-step @steps)]
    (flatten
     (for [[idx objs] submap]
       (for [[name func] objs]
         func)))))


(defn setup []
  (q/smooth)
  (q/frame-rate 1)
  (q/background BACKGROUND-COLOR))


(defn draw
  []
  (doseq [func (flatten-functions)]
    (func)))


(q/defsketch hangman
  :title "Hangman game"
  :setup setup
  :draw draw
  :size [640 480])
