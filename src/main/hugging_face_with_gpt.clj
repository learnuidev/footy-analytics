^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns hugging-face-with-gpt
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

;; # Hugging Face GPT With Clojure

;; Original Article [Link](http://gigasquidsoftware.com/blog/2020/01/10/hugging-face-gpt-with-clojure/)

;; ## Introduction

;; A new age in Clojure has dawned. We now have interop access to any python library with libpython-clj.


;; Let me pause a minute to repeat.


;; `You can now interop with ANY python library.`


;; I know. It’s overwhelming. It took a bit for me to come to grips with it too.


;; Let’s take an example of something that I’ve always wanted to do and have struggled with mightly finding a way to do it in Clojure:
;; I want to use the latest cutting edge GPT2 code out there to generate text.

;; Right now, that library is Hugging Face Transformers.


;; Get ready. We will wrap that sweet hugging face code in Clojure parens!

;; ## The setup

;; The first thing you will need to do is to have python3 installed and the two libraries that we need:
;; 1. pytorch – `pip3 install torch`
;; 2. hugging face transformers – `pip3 install transformers`

; Right now, some of you may not want to proceed.
; You might have had a bad relationship with Python in the past.
; It’s ok, remember that some of us had bad relationships with Java,
; but still lead a happy and fulfilled life with Clojure and still can
; enjoy it from interop. The same is true with Python. Keep an open mind.


; There might be some others that don’t want to have anything to do
; with Python and want to keep your Clojure pure. Well, that is a
; valid choice. But you are missing out on what the big, vibrant, and chaotic
; Python Deep Learning ecosystem has to offer.


; For those of you that are still along for the ride, let’s dive in.


; Your deps file should have just a single extra dependency in it:
(comment
  {:deps {org.clojure/clojure {:mvn/version "1.10.3"}
          clj-python/libpython-clj {:mvn/version "2.018"}}})

;; ## Diving Into Interop

;; The first thing that we need to do is require the libpython library.
(comment
  (ns hugging-face-with-gpt
    (:require [libpython-clj2.require :refer [require-python]]
              [libpython-clj2.python  :as py])))

;; It has a very nice `require-python` syntax that we will use
;; to load the `python libraries` so that we can use them in our Clojure code.

; (require-python 'transformers)
;;
(require-python '(transformers))
(require-python '(torch))

;; ### Step 1: Load pre-trained model tokenizer (vocabulary)
(comment
 (def tokenizer (py/$a transformers/GPT2Tokenizer from_pretrained "gpt2")))

;; Note: I get this error e when running above script: "No such var: transformers/GPT2Tokenizer"


(comment
  (clerk/serve! {:browse? true}))

(clerk/show! "src/main/hugging_face_with_gpt.clj")
