(ns matches
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]))

;; comment


(defn get-match-directory [competition-id]
   (clojure.java.io/file (str "open-data/data/matches/" competition-id)))

(comment
  (get-match-directory 11))

;;
;; matches directory
(def matches-directory (clojure.java.io/file "open-data/data/matches/"))

;; Give me all the season ids of la liga (competition_id = 1)
(comment
  (count (rest (map (fn [file] (.getName file)) (file-seq (get-match-directory 11))))))


;;
;; get data by filename
(defn get-match-data [competition-id season-id]
  (json/read-str (slurp (str "open-data/data/matches/" competition-id "/" (str season-id ".json")))
                 :key-fn keyword))

(comment
  "2004/2005 - 2008/2009: Data"
  ;; 04/05 - 7 matches
  (count (get-match-data 11 37))
  ;; 05/06 - 17 matches
  (count (get-match-data 11 38))
  ;; 06/07 - 26 matches
  (count (get-match-data 11 39))
  ;; 07/08 - 26 matches
  (count (get-match-data 11 40))
  ;; 08/09 - 31 matches
  (count (get-match-data 11 41)))

(comment
  "2019/2020: Data - 33 Matches"
  (count (get-match-data 11 42)))

(defn get-season [cid sid]
  (if-let [match (first (get-match-data cid sid))]
     (:season match)))

(comment
  "2009/2010 - 2015/2016"
  ;; 09/10 - 35 matches
  (get-season 11 21)
  (count (get-match-data 11 21))  ;; 35
  ;; 10/11 - 33 matches
  (get-season 11 22)
  (count (get-match-data 11 22)) ;; 33
  ;; 11/12 - 37 matches
  (get-season 11 23)
  (count (get-match-data 11 23))
  ;; 12/13 - 32 matches
  (get-season 11 24)
  (count (get-match-data 11 24))
  ;; 13/14 - 31 matches
  (get-season 11 25)
  (count (get-match-data 11 25))
  ;; 14/15 - 38 matches
  (get-season 11 26)
  (count (get-match-data 11 26))
  ;; 15/16 - 33 matches
  (get-season 11 27)
  (count (get-match-data 11 27)))

(comment
  "2016/2017 - 2020/2021"
  ;; 16/17 - 34 matches
  (get-season 11 2)
  (count (get-match-data 11 2))
  ;; 17/18 - 36 matches
  (get-season 11 1)
  (count (get-match-data 11 1))
  ;; 18/19 - 34 matches
  (get-season 11 4)
  (count (get-match-data 11 4))
  ;; 19/20 - 33 matches
  (get-season 11 42)
  (count (get-match-data 11 42))
  ;; 20/21 - 35 matches
  (get-season 11 90)
  (count (get-match-data 11 90)))


;;
(def seasons
  {44 03/04
   37 04/05
   38 05/06
   39 06/07
   40 07/08
   41 08/09
   21 09/10
   22 10/11
   23 11/12
   24 12/13
   25 13/14
   26 14/15
   27 15/16
   2  16/17
   3  2018
   1  17/18
   4  18/19
   30 2019
   42 19/20
   43 2020
   90 20/21})

;;
(comment
  (str/split "41.json" #"\.")
  (get seasons 27))

(comment
  "Give me all the season ids of champions league (competition_id = 16)"
  (map first (map #(str/split % #"\.") (rest (map (fn [file] (.getName file)) (file-seq (get-match-directory 16)))))))


(comment
  "get me all the available season data for champions league?"
  (map #(get seasons %) (map #(-> % first Integer/parseInt) (map #(str/split % #"\.") (rest (map (fn [file] (.getName file)) (file-seq (get-match-directory 16))))))))


(defn get-file-names [id]
   (rest (map (fn [file] (.getName file)) (file-seq (get-match-directory id)))))

(defn get-season-ids [id]
  (map #(-> % first Integer/parseInt) (map #(str/split % #"\.") (get-file-names id))))

(comment
 (get-season-ids 16))

(defn get-seasons [id]
  (map #(get seasons %) (get-season-ids id)))

;; Matches by competitions
(comment
 (get-seasons 2)   ;; premier league
 (get-seasons 11)  ;; la liga
 (get-seasons 16)  ;; champions league
 (get-seasons 37)  ;; FA Womens Super league
 (get-seasons 43)  ;; FIFA World Cup
 (get-seasons 49)  ;; NWSL - USA
 (get-seasons 55)  ;; UEFA Euro
 (get-seasons 72)) ;; Women's World Cup


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
