(ns statsbomb
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]))

(def base_url "https://raw.githubusercontent.com/statsbomb/open-data/master/data/")


(defn get_resource [url]
  (json/read-str (slurp url) :key-fn keyword))


(defn get_matches
  "Returns a list of all StatsBomb games in specified competition/season."
  [competition_id season_id base_url]
  (let [comp_url (str base_url  "matches/" competition_id "/" season_id ".json")]
    (get_resource comp_url)))

(comment
  (count (first (map :match_id (get_matches 11 2 base_url)))))

(defn- get_event_url [match_id]
  (str base_url "events/" match_id ".json"))

(defn get_events
  "Returns a list of all StatsBomb events in specified competition/season/match"
  [competition_id season_id match_id base_url]
  (get_resource (get_event_url match_id)))

(comment
  (get_events 11 2 266989 base_url))
