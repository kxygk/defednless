;; for https://old.reddit.com/r/Clojure/comments/1fpufs0/learn_document_math_in_clojure/
(add-libs {'thi.ng/geom {:mvn/version "1.0.1"}})

(require '[thi.ng.math.noise :as n])
(require '[thi.ng.geom.viz.core :as viz] :reload)
(require '[thi.ng.geom.svg.core :as svg])

(def viz-spec
  {:x-axis (viz/linear-axis
            {:domain [0, 63]
            :range  [50, 550]
            :major  8
            :minor  2
            :pos    550})
  :y-axis (viz/linear-axis
            {:domain      [0, 63]
            :range       [550, 50]
            :major       8
            :minor       2
            :pos         50
            :label-dist  15
            :label-style {:text-anchor "end"}})
  :data   [{:matrix       (->> (for [y (range 64)
                                      x (range 64)]
                                  (n/noise2 (* x
                                              0.06)
                                            (* y
                                              0.06)))
                                (viz/contour-matrix 64
                                                    64))
            :levels       (range -1
                                  1
                                  0.05)
            :value-domain [-1.0, 1.0]
            :attribs      {:fill "none"
                            :stroke "#0af"}
            :layout       viz/svg-contour-plot}]})

(-> viz-spec
    (viz/svg-plot2d-cartesian)
    svg/serialize
    (clojure.string/replace #"><"
                            ">\n<")
