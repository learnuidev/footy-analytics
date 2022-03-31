(ns clerk
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]))

;;
;; 1. Find all competitions
(defn find-all-competitions  []
  (json/read-str (slurp (str "open-data/data/competitions.json"))
                 :key-fn keyword))

(comment
  (count (find-all-competitions)))


(comment
  (clerk/serve! {:browse? true})
  (clerk/table (find-all-competitions))
  (clerk/show! "notebooks/rule_30.clj")
  (clerk/show! "notebooks/data_science.clj"))
