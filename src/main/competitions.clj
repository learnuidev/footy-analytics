(ns competitions
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]))

;; 1. Find all competitions
(defn find-all-competitions  []
  (json/read-str (slurp (str "open-data/data/competitions.json"))
                 :key-fn keyword))

(comment
  (count (find-all-competitions)))

;; 2. Tell me everything about la liga - 09/10 season
;; 11 - la liga
;; 21 - 2009/2010
(comment
  (filter #(and (= 11 (:competition_id %))
                (= 21  (:season_id %)))
          (find-all-competitions)))

;; 3. Give me all the la liga matches for 2009/2010 season
(defn find-all-matches [{:keys [competition_id season_id]}]
  (json/read-str (slurp (str "open-data/data/matches/" competition_id "/" (str season_id ".json")))
                 :key-fn keyword))

(comment
 (count (find-all-matches {:competition_id 11 :season_id 21})))


;; 4. Get me the match id of the first item in the list
(comment
  (:match_id (first (find-all-matches {:competition_id 11
                                       :season_id 21})))) ;; 69243

;; 5. Give me all the events related to match
(defn find-all-events [{:keys [match_id]}]
  (json/read-str (slurp (str "open-data/data/events/" (str match_id ".json")))
                 :key-fn keyword))

(comment
  (count (find-all-events {:match_id 69243})))

;; 6. LeGts explore events a bit. tell me about the location attribute
(comment
  (nth (find-all-events {:match_id 69243})
       10))
