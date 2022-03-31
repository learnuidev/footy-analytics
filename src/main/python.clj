(ns python
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]))

(comment
  ; (require-python 'mxnet
  ;                 '(mxnet ndarray module io model))
  (require-python 'cv2)
  (require-python '[numpy :as np])
  (require-python '[math :as pmath])
  (require-python '[matplotlib as mpl])
  (require-python '[matplotlib.pyplot as plt])
  (require-python '[scipy :as sp])
  (require-python '[pandas :as pandas]))

(comment
  (pandas/DataFrame []))

(comment
  (np/sqrt 100)
  (pmath/cos 1.5699999))

(comment
  (np/ones [1 2]))
  ;
; ;; How to use Python
; ;; Step 1: initialize the library
; (comment)
(py/initialize!)
;
; ;; Step 2: Usage
; ;; 1 Example: ->python
(comment
  (clojure.repl/doc py/->python))
;
; (comment)
(def test-dict (py/->python {:name "Jon Snow"}))
;
(comment
  (py/python-type test-dict))
