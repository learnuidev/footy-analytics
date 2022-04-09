^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns gigasquid.mxnet
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

;;
;;; sudo pip3 install mxnet
;;; sudo pip3 install opencv-python


;; Note: 10:28PM - 6th, April 2022  - doesnt work
(comment
  (require-python '[mxnet :as mxnet])
  (require-python '[mxnet.ndarray :as ndarray])
  (require-python '[mxnet.module :as module])
  (require-python '[mxnet.io :as io])
  (require-python '[mxnet.test_utils :as test-utils])
  (require-python '[mxnet.initializer :as initializer])
  (require-python '[mxnet.metric :as metric])
  (require-python '[mxnet.symbol :as sym]))
