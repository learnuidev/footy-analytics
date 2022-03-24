(ns matches
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]))

(defn get-match-directory [match-id]
   (clojure.java.io/file (str "open-data/data/matches/" match-id)))

;;
;; matches directory
(def matches-directory (clojure.java.io/file "open-data/data/matches/"))

;; file names of all the 360 data
(comment
  (count (rest (map (fn [file] (.getName file)) (file-seq matches-directory)))))


;;
;; get data by filename
(defn get-match-data [match-id filename]
  (json/read-str (slurp (str "open-data/data/matches/" match-id "/" filename))
                 :key-fn keyword))

(comment
  (get-match-data 2 "44.json"))

(defn browse-match-data [mid]
  (map #(get-match-data mid %)
       (rest (map (fn [file] (.getName file)) (file-seq (get-match-directory mid))))))

(comment
  (count (first (first (browse-match-data 2))))
  (count (first (browse-match-data 11))))




(s/def ::match_status_360 string?)
(s/def ::match_status string?)
(s/def ::match_week int?)


;;
(s/def ::home_score int?)
(s/def ::away_score int?)



;; stadium
(s/def ::id int?)
(s/def ::name string?)

;; country
(s/def ::country (s/keys :req-un [::id ::name]))
(s/def ::stadium (s/keys :req-un [::id ::name ::country]))

;; competition
(s/def ::competition_id int?)
(s/def ::country_name string?)
(s/def ::competition_name string?)
(s/def ::competition (s/keys :req-un [::competition_id ::country_name ::competition_name]))

(s/def ::match (s/keys :req-un [::match_status_360
                                ::match_status
                                ::match_week
                                ;; Home
                                ::home_score
                                ::away_score
                                ;; competition
                                ::competition
                                ;; stadium
                                ::stadium]))

(comment
  (s/valid? ::match (first (first (browse-match-data 2))))
  (s/valid? ::match (:competition (first (first (browse-match-data 2)))))
  (s/explain ::match (:competition (first (first (browse-match-data 2)))))
  (s/valid? ::competition (:competition (first (first (browse-match-data 2)))))
  (s/valid? ::stadium (:stadium (first (first (browse-match-data 2))))))
