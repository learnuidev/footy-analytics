^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns gigasquid.spacy
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

;;
;;;; What is SpaCy?

;;; also natural language toolkit https://spacy.io/usage/spacy-101#whats-spacy
;;; opinionated library and more Object oriented than NLTK. Has word vector support
;;; better performance for tokenization and pos tagging (source https://medium.com/@akankshamalhotra24/introduction-to-libraries-of-nlp-in-python-nltk-vs-spacy-42d7b2f128f2)

;;; Install pip3 install spacy
;;;; python3 -m spacy download en_core_web_sm

(require-python '[spacy :as spacy])
