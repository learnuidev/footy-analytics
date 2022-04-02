^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns parens-for-plot
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

(comment
 (clerk/serve! {:browse? true}))

;; # Parens for Pyplot
;; Original Article [Link](http://gigasquidsoftware.com/)

; libpython-clj has opened the door for Clojure to directly interop with Python libraries.
; That means we can take just about any Python library and directly use it in our Clojure REPL.
; But what about matplotlib?

; Matplotlib.pyplot is a standard fixture in most tutorials and python data science code.
; How do we interop with a python graphics library?

; ## How do you interop?

; It turns out that matplotlib has a headless mode where we can export the graphics and then display it using any method that we would normally use to display a .png file.
; In my case, I made a quick macro for it using the shell open.
; I’m sure that someone out that could improve upon it,
; (and maybe even make it a cool utility lib), but it suits what I’m doing so far:

(def mplt (py/import-module "matplotlib"))

(py/py. mplt "use" "Agg")

(require-python 'matplotlib.pyplot)
(require-python 'matplotlib.backends.backend_agg)
(require-python 'numpy)
; (require-python '[numpy :as numpy])

(defmacro with-show
  "Takes forms with mathplotlib.pyplot to then show locally"
  [& body]
  `(let [_# (matplotlib.pyplot/clf)
         fig# (matplotlib.pyplot/figure)
         agg-canvas# (matplotlib.backends.backend_agg/FigureCanvasAgg fig#)]
     ~(cons 'do body)
     (py/py. agg-canvas# "draw")
     (matplotlib.pyplot/savefig "temp.png")
     (sh/sh "open" "temp.png")))

;; ## Parens for Pyplot!

;; Now that we have our wrapper let’s take it for a spin. We’ll be following along more or less this tutorial for numpy plotting

;; For setup you will need the following installed in your python environment:

; - numpy
; - matplotlib
; - pillow


;; ### Example 1 - Simple Sin and Cos

; Let’s start off with a simple `sine` and `cosine` functions.
; This code will create a x `numpy` vector of a
; range from 0 to 3 * `pi` in 0.1 increments and then create y numpy `vector` of the sin of that and plot it

(comment
  (let [x (numpy/arange 0 (* 3 numpy/pi) 0.1)
          y (numpy/sin x)]
      (with-show
        (matplotlib.pyplot/plot x y))))

;; ### Example 2 - Lables, title and legend
;; Beautiful yes!
;; Let’s get a bit more complicated now and and plot both the sin and cosine as well as add labels, title, and legend.
(comment
  (let [x (numpy/arange 0 (* 3 numpy/pi) 0.1)
        y-sin (numpy/sin x)
        y-cos (numpy/cos x)]
    (with-show
      (matplotlib.pyplot/plot x y-sin)
      (matplotlib.pyplot/plot x y-cos)
      (matplotlib.pyplot/xlabel "x axis label")
      (matplotlib.pyplot/ylabel "y axis label")
      (matplotlib.pyplot/title "Sine and Cosine")
      (matplotlib.pyplot/legend ["Sine" "Cosine"]))))

;; ### Example 3 - Subplots
;; We can also add subplots. Subplots are when you divide the plots into different portions. It is a bit stateful and involves making one subplot active and making changes and then making the other subplot active. Again not too hard to do with Clojure.
(comment
  (let [x (numpy/arange 0 (* 3 numpy/pi) 0.1)
        y-sin (numpy/sin x)
        y-cos (numpy/cos x)]
    (with-show
      ;;; set up a subplot gird that has a height of 2 and width of 1
      ;; and set the first such subplot as active
      (matplotlib.pyplot/subplot 2 1 1)
      (matplotlib.pyplot/plot x y-sin)
      (matplotlib.pyplot/title "Sine")

      ;;; set the second subplot as active and make the second plot
      (matplotlib.pyplot/subplot 2 1 2)
      (matplotlib.pyplot/plot x y-cos)
      (matplotlib.pyplot/title "Cosine"))))

;; ### Example 4 Plotting with Images

;; #### 4.1 Human Neuron
;; Pyplot also has functions for working directly with images as well.
;; Here we take a picture of neuron and create another version of it that is tinted.
(comment
  (let [img (matplotlib.pyplot/imread "resources/neuron.jpg")
        img-tinted (numpy/multiply img [1 0.95 0.9])]
    (with-show
      (matplotlib.pyplot/subplot 1 2 1)
      (matplotlib.pyplot/imshow img)
      (matplotlib.pyplot/subplot 1 2 2)
      (matplotlib.pyplot/imshow (numpy/uint8 img-tinted)))))

;; #### 4.2 Mycorrhizal symbiosis
(comment
  (let [img (matplotlib.pyplot/imread "resources/symbiosis.jpeg")
        img-tinted (numpy/multiply img [1 0.95 0.9])]
    (with-show
      (matplotlib.pyplot/subplot 1 2 1)
      (matplotlib.pyplot/imshow img)
      (matplotlib.pyplot/subplot 1 2 2)
      (matplotlib.pyplot/imshow (numpy/uint8 img-tinted)))))


;; ### Example 5 Pie charts

;; Finally, we can show how to do a pie chart. I asked people in a twitter thread what they wanted an example of in python interop and one of them was a pie chart. This is for you!
;; The original code for this example came from this tutorial.

(comment
  (let [labels ["Frogs" "Hogs" "Dogs" "Logs"]
        sizes [15 30 45 10]
        explode [0 0.1 0 0]] ; only explode the 2nd slice (Hogs)
      (with-show
        (let [[fig1 ax1] (matplotlib.pyplot/subplots)]
          (py/py. ax1 "pie" sizes :explode explode :labels labels :autopct "%1.1f%%"
                               :shadow true :startangle 90)
          ;; equal aspec ration ensures that pie is drawn as circle
          (py/py. ax1 "axis" "equal")))))

;;
;; ### Onwards and Upwards!
;; This is just the beginning. In upcoming posts, I will be showcasing examples of interop with different libraries from the python ecosystem. Part of the goal is to get people used to how to use interop but also to raise awareness of the capabilities of the python libraries out there right now since they have been historically out of our ecosystem.

;; If you have any libraries that you would like examples of, I’m taking requests. Feel free to leave them in the comments of the blog or in the twitter thread.

;; Until next time, happy interoping!

;; PS All the code examples are here https://github.com/gigasquid/libpython-clj-examples

(clerk/show! "src/main/parens_for_plot.clj")

;
