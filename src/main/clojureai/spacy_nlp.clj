;; ## Clerk Notebook Setup
(comment
 (clerk/serve! {:browse? true})
 (clerk/show! "src/main/clojureai/spacy_nlp.clj"))

^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns clojureai.spacy-nlp
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

;; ---

;; ## What’s spaCy?
;; spaCy is a free, open-source library for advanced Natural Language
;; Processing (NLP) in Python. We are going to use libpython-clj
;; library to access the library from Clojure



(comment
 (require-python '[spacy :as spacy]))
(comment
  (spacy/blank "en"))
 ;; if you get this error "ModuleNotFoundError: No module named 'spacy'\n"
 ;; run: pip3 install --user -U spacy
