(ns bookcamp
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]))

(comment
 (clerk/serve! {:browse? true}))
(clerk/show! "datascience_bookcamp_clj/01_computing_probabilities.clj")

(comment
 (clerk/show! "notebooks/semantic.clj"))
