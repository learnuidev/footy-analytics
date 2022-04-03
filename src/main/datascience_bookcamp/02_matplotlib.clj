^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns datascience-bookcamp.02-matplotlib
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [libpython-clj2.python  :as py]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]
            [libpython-clj2.require :refer [require-python]]))

(def weighted-sample-space {:heads 4 :tails 1})

; ## Plotting probabilities using Matplotlib

; **Reference**
; - Book: **[DataScience Bookcamp: Five Python Projects](https://www.manning.com/books/data-science-bookcamp)**

; 6:35PM - This is my study notes for second Chapter from the book **[DataScience Bookcamp: Five Python Projects](https://www.manning.com/books/data-science-bookcamp)**, with one minor difference. All the code examples are in `Clojure`.

; **Learning Objects**
; - Creating simple plots using Matplotlib
; - Labeling plotted data
; - What is a probability distribution?
; - Plotting and comparing multiple probability distributions


; **Key Words**: *simple plots, matplotlib, probability distribution*

; ## 2.1 Basic Matlib Plots

;; ### 2.1.1 import module

;; Import matplotlib.pyplot, which is the library’s main plot-generation module.
;; According to convention, the module is commonly imported using the shortened alias plt.

(require-python 'matplotlib.pyplot)
(require-python 'numpy)
(require-python 'matplotlib.backends.backend_agg)

;;
(defmacro with-show
  "Takes forms with mathplotlib.pyplot to then show locally"
  [& body]
  `(let [_ (matplotlib.pyplot/clf)
         fig (matplotlib.pyplot/figure)
         agg-canvas (matplotlib.backends.backend_agg/FigureCanvasAgg fig)]
     ~(cons 'do body)
     (py/py. agg-canvas "draw")
     (matplotlib.pyplot/savefig "temp.png")
     (sh/sh "open" "temp.png")))

;;
(comment
  (let [x (numpy/arange 0 (* 3 numpy/pi) 0.1)
        y (numpy/sin x)]
      (with-show
        (matplotlib.pyplot/plot x y))))

(clerk/show! "src/main/datascience_bookcamp/02_matplotlib.clj")
