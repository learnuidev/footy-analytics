(ns clerk
  (:require [nextjournal.clerk :as clerk]))


(comment
  (clerk/serve! {:browse? true})
  (clerk/show! "notebooks/rule_30.clj"))
