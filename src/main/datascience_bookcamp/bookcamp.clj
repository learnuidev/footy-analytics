(ns datascience-bookcamp.bookcamp
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]))

(comment
 (clerk/serve! {:browse? true})
 (clerk/show! "src/main/datascience_bookcamp/01_computing_probabilities.clj")
 (clerk/show! "src/main/datascience_bookcamp/02_matplotlib.clj")
 (clerk/show! "src/main/datascience_bookcamp/03_statistics.clj"))

(comment
 (clerk/show! "notebooks/semantic.clj")
 (clerk/show! "src/main/gigasquid/parens_for_plot.clj"))
