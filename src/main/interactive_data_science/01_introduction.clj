(ns interactive-data-science.01-introduction)

;; # Introduction

;; Congratulations! You’ve just been hired to lead the data science efforts at
;; Novus, a next generation collaborative learning and knowledge sharing platform

;; Welcome aboard, and good luck

;; ## Finding Key Connectors

;; It’s your first day on the job at Novus,
;; and the VP of Networking is full of questions about your users.
;; Until now he’s had no one to ask, so he’s very excited to have you aboard.
;; In particular, he wants you to identify who the “key connectors” are
;; among learners. To this end, he gives you a dump of the entire Novus
;; network. (In real life, people don’t typically hand you the data you need)

;; What does this data dump look like? It consists of a list of users,
;; each represented by a map that contains that user’s `:id`
;; (which is a number) and `:name` (which, in one of the great cosmic coincidences,
;; rhymes with the user’s id:

(def users
  [{:id 0 :name "Hero"}
   {:id 1 :name "Dunn"}
   {:id 2 :name "Sue"}
   {:id 3 :name "Chi"}
   {:id 4 :name "Thor"}
   {:id 5 :name "Clive"}
   {:id 6 :name "Hicks"}
   {:id 7 :name "Devin"}
   {:id 8 :name "Kate"}
   {:id 9 :name "Klein"}])

;; He also gives you the “friendship” data, represented as a list of pairs of IDs:
(def friendship_pairs
  [[0 1]
   [0 2]
   [1 2]
   [1 3]
   [2 3]
   [3 4]
   [4 5]
   [5 6]
   [5 7]
   [6 8]
   [7 8]
   [8 9]])

;; For example, the vector [0, 1] indicates that the student with
;; id 0 (Hero) and the student with id 1 (Dunn) are friends.
;; The network is illustrated in Figure 1-1.

;; TODO: Figure 1-1 goes here


;; Having friendships represented as a list of pairs is not the
;; easiest way to work with them. To find all the friendships for user 1,
;; you have to iterate over every pair looking for pairs containing 1.
;; If you had a lot of pairs, this would take a long time.

;; Instead, let’s create a `map` where the keys are user ids and the values
;; are lists of friend ids. (Looking things up in a map is very fast.)”
