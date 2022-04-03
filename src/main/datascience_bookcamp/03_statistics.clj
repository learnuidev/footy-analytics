; 11:29PM - 2nd April, 2022
^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns datascience-bookcamp.03-statistics
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [libpython-clj2.python  :as py]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]
            [libpython-clj2.require :refer [require-python]]))

; **Reference**

; Book: **[Data Science from Scratch, 2nd Edition](https://www.manning.com/books/data-science-bookcamp)**

; This is my study notes for 5th Chapter from the book **[Data Science from Scratch, 2nd Edition](https://www.manning.com/books/data-science-bookcamp)**, with one minor difference.

;; All the code examples are in `Clojure`.

;; ---

; # Statistics

; > Facts are stubborn, but statistics are more pliable

; Author: `Mark Twain`

; Statistics refers to the mathematics and techniques with which we
; understand data. It is a rich, enormous field, more suited to a
; shelf (or room) in a library than a chapter in a book, and so our
; discussion will necessarily not be a deep one. Instead, I’ll try to
; teach you just enough to be dangerous, and pique your interest just
; enough that you’ll go off and learn more.

;; ## Describing a Single Set of Data

;; Through a combination of word of mouth and luck, DataSciencester has grown to dozens of members, and the VP of Fundraising asks you for some sort of description of how many friends your members have that he can include in his elevator pitches.
; Using techniques from Chapter 1, you are easily able to produce this data. But now you are faced with the problem of how to describe it.
; One obvious description of any dataset is simply the data itself:




(clerk/show! "src/main/datascience_bookcamp/03_statistics.clj")
