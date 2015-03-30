(ns hangman.graphics
  (:require [hangman.graphics :refer :all]
            [clojure.test :refer :all]))


; define a screen, where all the action takes place
(defscreen main-screen
  ; all the screen functions get a map called "screen" containing various
  ; important values, and a vector called "entities" for storing game objects

  ; the entities vector is immutable, so in order to update it you must simply
  ; return a new vector at the end of each screen function

  ; this function runs only once, when the screen is first shown
  :on-show
  (fn [screen entities]
    ; update the screen map to hold a tiled map renderer and a camera
    (update! screen
             :renderer (orthogonal-tiled-map "level1.tmx" (/ 1 8))
             :camera (orthographic))
    (let [; load a sprite sheet from your resources dir
          sheet (texture "tiles.png")
          ; split the sheet into 16x16 tiles
          ; (the texture! macro lets you call TextureRegion methods directly)
          tiles (texture! sheet :split 16 16)
          ; get the tile at row 6, col 0
          player-image (texture (aget tiles 6 0))
          ; add position and size to the player-image map so it can be drawn
          player-image (assoc player-image :x 0 :y 0 :width 2 :height 2)]
      ; return a new entities vector with player-image inside of it
      [player-image]))

  ; this function runs every time a frame must be drawn (about 60 times per sec)
  :on-render
  (fn [screen entities]
    ; make the screen completely black
    (clear!)
    ; render the tiled map, draw the entities and return them
    (render! screen entities))

  ; this function runs when the screen dimensions change
  :on-resize
  (fn [screen entities]
    ; make the camera 20 tiles high, while maintaining the aspect ratio
    (height! screen 20)
    ; you can return nil if you didn't change any entities
    nil))

; define the game itself, and immediately hand off to the screen
(defgame game-test
  :on-create
  (fn [this]
    (set-screen! this main-screen)))
