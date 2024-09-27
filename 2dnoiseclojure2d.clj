;; from https://old.reddit.com/r/Clojure/comments/1fpufs0/learn_document_math_in_clojure/
(add-libs {'clojure2d/clojure2d {:mvn/version "1.5.0-SNAPSHOT"}})

(require '[clojure2d.core :as c2d])
(require '[clojure2d.color :as c])
(require '[clojure2d.extra.utils :as u])
(require '[fastmath.random :as r])
(require '[clojure.math :as m])

(def gradient (c/gradient :pals/ocean.ice))

(defn draw-noise
  "Loop through noise field and draw it."
  [n]
  (c2d/with-canvas [canvas (c2d/canvas 800 800 :low)]
    (c2d/set-background canvas :black)
    (dotimes [y 760]
      (dotimes [x 760]
        (let [xx (/ x 100.0)
              yy (/ y 100.0)
              nn (m/sqrt (n xx yy))] ;; cheap brightness
          (c2d/set-color canvas (gradient nn))
          (c2d/rect canvas (+ x 20) (+ y 20) 1 1))))
    canvas))

(u/show-image (draw-noise (r/billow-noise)))
