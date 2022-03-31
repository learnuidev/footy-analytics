(ns wyscout
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]))


;; https://figshare.com/collections/Soccer_match_event_dataset/4415000

(comment
  (count (json/read-str (slurp "wyscout/teams.json")
                        :key-fn keyword))
  (last (json/read-str (slurp "wyscout/competitions.json")
                       :key-fn keyword))
  (count (json/read-str (slurp "wyscout/matches/matches_World_Cup.json")
                        :key-fn keyword)))




(comment
  (slurp "https://figshare.com/articles/dataset/Coaches/8082650?backTo=/collections/Soccer_match_event_dataset/4415000")
  (slurp "https://figshare.com/ndownloader/files/15073868"))

;;
(comment
  (count (json/read-str (slurp "https://figshare.com/ndownloader/files/15073697")
                        :key-fn keyword))
  (count (json/read-str (slurp "https://figshare.com/ndownloader/files/15073685")
                        :key-fn keyword))
  (count (json/read-str (slurp "https://figshare.com/ndownloader/files/14464622")
                        :key-fn keyword)))
