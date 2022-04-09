(ns soccermatics.02-plot-shots-and-passes
  (:require [libpython-clj2.require :refer [require-python]]
            [tech.v3.dataset :as ds]
            [libpython-clj2.python  :as py]
            [clojure.data.json :as json]
            [soccermatics.01-load-in-data :as loader]
            [tablecloth.api :as tc]
            [tech.v3.datatype.functional :as dfn]
            [tech.v3.datatype :as dtype]))

;;
(defn read-str [link]
  (json/read-str (slurp link)
                 :key-fn keyword))


(def mplt (py/import-module "matplotlib"))

(py/py. mplt "use" "Agg")

(require-python 'matplotlib.pyplot)
(require-python 'matplotlib.backends.backend_agg)
(require-python 'numpy)
(require-python '[pandas.io.json :refer [json_normalize]])


(defmacro with-show
  "Takes forms with mathplotlib.pyplot to then show locally"
  [& body]
  `(let [_# (matplotlib.pyplot/clf)
         fig# (matplotlib.pyplot/figure)
         agg-canvas# (matplotlib.backends.backend_agg/FigureCanvasAgg fig#)]
     ~(cons 'do body)
     (py/py. agg-canvas# "draw")
     (matplotlib.pyplot/savefig "temp.png")
     (sh/sh "open" "temp.png")))

;;
(comment
  (json/load "open-data/data/events/69301.json")
  (count (loader/find-all-events {:match_id 69301})))

;;
(comment
 (def event-data (ds/->dataset (read-str "open-data/data/events/69301.json")))
 (take 20 (ds/mapseq-reader event-data))
 (ds/mapseq-reader (ds/descriptive-stats event-data)))


(comment
 (def csv-data (ds/->dataset "https://github.com/techascent/tech.ml.dataset/raw/master/test/data/stocks.csv"
                             {:key-fn keyword}))

 (ds/head csv-data)
 (take 2 (ds/mapseq-reader csv-data))
 (println "price mean:" (dfn/mean (csv-data :price))))

;;
(comment
  (-> "https://raw.githubusercontent.com/techascent/tech.ml.dataset/master/test/data/stocks.csv"
      (tc/dataset {:key-fn keyword})
      ds/mapseq-reader
      #_(tc/group-by (fn [row]
                        {:symbol (:symbol row)
                         :year (:date row)}))))
      ; (tc/aggregate #(tech.v3.datatype.functional/mean (% :price)))
      ; (tc/order-by [:symbol :year])
      ; (tc/head 10)))
