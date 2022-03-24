(ns tablecloth
  (:require [tech.v3.dataset :as ds]
            [tech.v3.datatype :as dtype]
            [clojure.repl :as repl]
            [clojure.data.json :as json]))

(defn read-str [link]
  (json/read-str (slurp link)
                 :key-fn keyword))


;; https://techascent.github.io/tech.ml.dataset/walkthrough.html
(comment
  (repl/doc ds/->>dataset)
  (ds/->dataset "https://github.com/techascent/tech.ml.dataset/raw/master/test/data/ames-train.csv.gz"
                    {:column-whitelist ["SalePrice" "1stFlrSF" "2ndFlrSF"]
                     :n-records 5}))

(comment
  (ds/->dataset (json/read-str (slurp "https://github.com/statsbomb/open-data/blob/master/data/competitions.json"))))

;; 1. Data creation
(comment
  "Data Creation: ->dataset"
  (first (ds/->dataset (read-str "open-data/data/competitions.json")))
  ((:competition_name (ds/->dataset (read-str "open-data/data/competitions.json")))
   0)
  ((:match_updated (ds/->dataset (read-str "open-data/data/competitions.json")))
   0))


;;
(comment
  (ds/->dataset "https://github.com/techascent/tech.ml.dataset/raw/master/test/data/ames-train.csv.gz"
                    {:column-whitelist ["SalePrice" "1stFlrSF" "2ndFlrSF"]
                     :n-records 5}))
