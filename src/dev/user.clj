(ns user
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [statsbomb]
            [clerk]
            [python]
            [bookcamp]))

;; Part 1 - Data Exploring
;; ======= 360
;; testing
(comment
  (json/read-str (slurp "open-data/data/three-sixty/3788741.json")))

;; 360 directory
(def directory (clojure.java.io/file "open-data/data/three-sixty/"))

;; file names of all the 360 data
(comment
  (map (fn [file] (.getName file)) (file-seq directory)))

;; get data by filename
(defn get-360-data [filename]
  (json/read-str (slurp (str "open-data/data/three-sixty/" filename))))

(comment
  (map get-360-data
       (rest (map (fn [file] (.getName file)) (file-seq directory)))))


;; ====== MAtches
;; Q: Why Clojure map keys are keywords?
(comment
  (map "season" (json/read-str (slurp "open-data/data/matches/2/44.json")))
  (map #(get % "season") (json/read-str (slurp "open-data/data/matches/2/44.json")))
  (map :season (json/read-str (slurp "open-data/data/matches/2/44.json")
                              :key-fn keyword))
  (map #(get-in % [:season :season_name]) (json/read-str (slurp "open-data/data/matches/2/44.json")
                                                         :key-fn keyword)))

;; Insight
(comment
  "matches/2/44.json
   44.json
     - league: EPL
     - season: 2003/2005")


;; ============
(comment
 (slurp "kaggle/height_mf_country_2022.csv")
 (filter #(= "Bhutan" (:country %)) (map
                                      (fn [[rank country mh fh mhf fhf]]
                                        {:rank rank
                                         :country country
                                         :male/height {:cm mh
                                                       :ft mhf}
                                         :female/height {:cm fh
                                                         :ft fhf}})
                                      (rest (with-open [reader (io/reader "kaggle/height_mf_country_2022.csv")]
                                             (doall
                                               (csv/read-csv reader)))))))
