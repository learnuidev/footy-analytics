(ns decodebhutan.core
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [dk.ative.docjure.spreadsheet :as sp]))

;; Live Stock Stats
(comment
  ;; Attempt 1 - Fail
  (csv/read-csv (slurp (str "decodebhutan/LS2020.xlsx"))
                :key-fn keyword)
  ;; Attempt 2
  (->> (sp/load-workbook "decodebhutan/LS2020.xlsx")
       (sp/sheet-seq)
       (mapcat sp/cell-seq)
       (map sp/read-cell)
       (filter boolean)))
       ; (sp/select-sheet "second")
       ; sp/row-seq))

(def bovine #{"Cattle" "Mithun" "Yak" "Zo/Zom" "Buffalo"})
(def other #{"Equine" "Pig" "Poultry" "Sheep" "Goat" "Cat" "Dog Pet/Utility"})

(comment
  (->> (sp/load-workbook "decodebhutan/LS2020.xlsx")
       (sp/select-sheet "T2.2")
       sp/row-seq
       (mapcat sp/cell-seq)
       (map sp/read-cell)
       (filter boolean)))

;; ===== Labor Force Stats ===
(comment
  (->> (sp/load-workbook "decodebhutan/LFSR_2020.xlsx")
       (sp/select-sheet "Tab A2.1")
       sp/row-seq
       (mapcat sp/cell-seq)
       (map sp/read-cell)
       (filter boolean)))


;; === Agriculture Stats
(comment
  (->> (sp/load-workbook "decodebhutan/AS2020.xlsx")
       (sp/sheet-seq)
       (mapcat sp/cell-seq)
       (map sp/read-cell)
       (filter boolean)))
