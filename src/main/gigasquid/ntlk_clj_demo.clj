^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns gigasquid.ntlk-clj-demo
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python  :as py]
            [tech.v3.datatype :as dtype]
            [clojure.java.shell :as sh]
            [nextjournal.clerk :as clerk]))

(comment
 (clerk/serve! {:browse? true}))

;; # Clojure Interop With Python NLP Libraries
;; Original Article [Link](http://gigasquidsoftware.com/blog/2020/01/24/clojure-interop-with-python-nlp-libraries/)


;; In this edition of the blog series of Clojure/Python interop with libpython-clj, we’ll be taking a look at two popular Python NLP libraries: NLTK and SpaCy.

;; ## NLTK – Natural Language Toolkit

;; I was taking requests for doing examples of python-clojure
;; interop libraries on twitter the other day, and by far NLTK
;; was the most requested library. After looking into it, I can see why.
;; It’s the most popular natural language processing library in Python and
;; you will see it everywhere there is text someone is touching.


;; ## Installation
; To use the NLTK toolkit you will need to install it. I use sudo pip3 install nltk, but libpython-clj now supports virtual environments with this PR, so feel free to use whatever is best for you.

;; ## Features
; We’ll take a quick tour of the features of NLTK following along initially with the nltk official book and then moving onto this more data task centered tutorial.

; First, we need to require all of our things as usual:
(comment
  (require-python 'nltk))

;; ## Downloading packages

;; There are all sorts of packages available to download from NLTK.
;; To start out and tour the library, I would go with a small one that
;; has basic data for the nltk book tutorial.

(comment
 (nltk/download "book")
 (require-python '[nltk.book :as book]))

;; There are all other sorts of downloads as well,
;; such as `(nltk/download "popular")` for most used ones.
;; You can also download "all", but beware that it is big.

;; You can check out some of the texts it downloaded with:

(comment
  (book/texts))

;; You can do fun things like see how many tokens are in a text
(count (py/py.- book/text3 tokens))

;; Or even see the lexical diversity, which is a measure of the richness of the text by looking at the unique set of word tokens against the total tokens.
;;
(defn lexical-diversity [text]
    (let [tokens (py/py.- text tokens)]
      (/ (-> tokens set count)
         (* 1.0 (count tokens)))))

(lexical-diversity book/text3)
;=> 0.06230453042623537

(lexical-diversity book/text5)
;=> 0.13477005109975562

;; This of course is all very interesting but I prefer to look at some more practical tasks, so we are going to look at some sentence tokenization.


;; ## Sentence Tokenization
;; Text can be broken up into individual word tokens or sentence tokens. Let’s start off first with the token package
(require-python '[nltk.tokenize :as tokenize])

(def text
   "Hello Mr. Smith, how are you doing today?
    The weather is great, and city is awesome.
    The sky is pinkish-blue.
    You shouldn't eat cardboard")

;; To tokenize sentences, you take the text and use `tokenize/sent_tokenize`.
(comment
 (clojure.repl/doc tokenize/sent_tokenize))

(def tokenized-sent (tokenize/sent_tokenize text))

;; Likewise, to tokenize words, you use `tokenize/word_tokenize`:
(def tokenized-word (tokenize/word_tokenize text))


;;; ## Frequency Distribution

;; You can also look at the frequency distribution of the words with using the probability package.

(require-python '[nltk.probability :as probability])


(def fdist (probability/FreqDist tokenized-word))

(py/py. fdist most_common)

;; ## Stop Words

;; Stop words are considered noise in text and there are ways to use the library to remove them using the nltk.corpus.
(require-python '[nltk.corpus :as corpus])

(def stop-words (into #{} (py/py. corpus/stopwords words "english")))

;; Now that we have a collection of the stop words, we can filter them out of our text in the normal way in Clojure.
(def filtered-sent (->> tokenized-sent
                         (map tokenize/word_tokenize)
                         (map #(remove stop-words %))))

(comment
 '(("Hello" "Mr." "Smith" "," "today" "?")
   ("The" "weather" "great" "," "city" "awesome" ".")
   ("The" "sky" "pinkish-blue" ".")
   ("You" "n't" "eat" "cardboard")))

;; ## Lexion Normalization and Lemmatization

;; Stemming and Lemmatization allow ways for the text to be reduced
;; to base words and normalized. For example, the word `flying` has a
;; stemmed word of `fli` and a lemma of `fly`.

(clerk/show! "src/main/gigasquid/ntlk_clj_demo.clj")









;
