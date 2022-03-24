(ns events
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

;;
;; matches directory
(def events-directory (clojure.java.io/file "open-data/data/events/"))

;; file names of all the 360 data
(comment
  (count (map (fn [file] (.getName file)) (file-seq events-directory))))


;;
;; get data by filename
(defn get-event-data [filename]
  (json/read-str (slurp (str "open-data/data/events/" filename))))

(comment
  (get-event-data "2275050.json"))

(defn browse-event-data []
  (map get-event-data
       (rest (map (fn [file] (.getName file)) (file-seq events-directory)))))

(comment
  (first (first (browse-event-data)))
  (= (first (browse-event-data))
     (get-event-data "2275050.json")))
