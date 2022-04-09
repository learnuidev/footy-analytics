^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns gigasquid.pytorch
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

;;
; (def mplt (py/import-module "torchvision"))

(comment
  (require-python
   '[torch :as torch]
   '[torch.cuda :as cuda]
   '[torch.onnx :as onnx]
   '[torch.nn :as nn :refer [Conv2d Dropout2d Linear]]
   '[torch.optim :as optim]
   '[torch.utils.data :as tud]
   '[torch.nn.functional :as F]
   ; '[torchvision.datasets :as datasets]
   ; '[torchvision.transforms :as transforms]
   '[torch.optim.lr_scheduler :as lr_scheduler])
    ;
 (require-python '[spacy :as spacy]))
