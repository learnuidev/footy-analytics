(ns wyscout
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]))


(comment
  (count (json/read-str (slurp "wyscout/teams.json")
                        :key-fn keyword))
  (last (json/read-str (slurp "wyscout/competitions.json")
                       :key-fn keyword))
  (count (json/read-str (slurp "wyscout/matches/matches_World_Cup.json")
                        :key-fn keyword)))
