^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns gigasquid.seahorn
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))


;;
(comment
 (require-python '[seaborn :as sns])
 (require-python '[matplotlib.pyplot :as pyplot]))

;;; What is seaborn? Really cool statistical plotting

;;; sudo pip3 install seaborn
