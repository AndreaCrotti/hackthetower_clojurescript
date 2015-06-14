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

(def showing-order
  {0 {:base
      (fn [] (q/rect (- (/ WIDTH 2) (/ (:base sizes) 2))
                    (- HEIGHT (:base sizes))
                    (:base sizes)
                    (:base sizes)))}

   1 {:stick
      (fn []
        (let [x (/ WIDTH 2)
              y (- HEIGHT (:base sizes))]
          
          (q/line x y x (- y (:stick sizes)))))}

   2 {:hook (fn [] (q/line 1 2))}})

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
    (print func)
    (func)))


(q/defsketch hangman
  :title "Hangman game"
  :setup setup
  :draw draw
  :size [640 480])
