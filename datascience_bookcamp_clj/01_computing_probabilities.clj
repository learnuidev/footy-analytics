^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns datascience-bookcamp-clj.01-computing-probabilities
  (:require [clojure.data.json :as json]
            [clojure.data.csv :as csv]
            [clojure.pprint :as pprint]
            [clojure.java.io :as io]
            [nextjournal.clerk :as clerk]))

; ## Computing Probabilities in Clojure

; **Reference**
; - Book: **[DataScience Bookcamp: Five Python Projects](https://www.manning.com/books/data-science-bookcamp)**

; 6:35PM - This is my study notes for first Chapter from the book **[DataScience Bookcamp: Five Python Projects](https://www.manning.com/books/data-science-bookcamp)**, with one minor difference. All the code examples are in `Clojure`.

; **Learning Objects**
; - What is the basics of Probability Theory?
; - Computing probabilities of a single observation
; - Computing probabilities across a range of observations

; **Key Words**: *compute, probability theory, observation, sample space, event, event condition*

;; Few things in life are certain; most things are driven by chance.

;; Nothing in life is guranteed - 2018/2019 was a crazy season for Liverpool FC. It was also season where Jurgen Kloop's Liverpool FC reached the finals of Champions League. I was estatic. I love watching Jurgen Kloops Liverpool. They play really exciting football. They lost 3-1 - all good though - next year they won it and this time Salah did score #6ix

;; **What happens every 300 years?**
;; > A total solar eclipse occurs only once every 300 years or so. Because the shadow cast by the Earth is quite a bit larger than the Moon, lunar eclipses are more common than solar eclipses, and totality can last for about an hour.

;; Randomness permeates our day-to-day experiences.

;; Fortunately, we cxan migitate and control randomness.

;; We know **two** things
;; 1. We know that unpredictable events such as witnessing a shooting star is rarer than witnessing sunset. Some things are more predictable than others
;; 2. we know that certain decisions carry less uncertainty than other much-riskier choices.
;; Riding a motorcycle without helmet is more riskier than riding with with one.
;; Backpacking in europe is generally a safer bet than backpacking in Canada.
;; Since Europe is small, you can travel more countries and do more activities

;; Even the most unpredictable systems still show some predictable behaviors.
;; Mathematicians have studied these unpredictable behaviours rigorously using probability theory.

;; > Probability theory is an inherently complex branch of math. However, aspects of the theory can be understood without knowing the mathematical underpinnings. In fact, difficult probability problems can be solved in Clojure without needing to know a single math equation. Such an equation-free approach to probability requires a baseline understanding of what mathematicians call a **sample space**.
; ## 1.1 Sample space analysis: An equation-free approach for measuring uncertainty in outcomes

; - A sample space is the set of all the possible outcomes an action could produce.
; - Let’s take the simple action of flipping a coin. The coin will land on either heads or tails. Thus, the coin flip will produce one of two measurable outcomes: heads or tails. By storing these outcomes in a Clojure set, we can create a sample space of coin flips.

; ##### 1.1 Creating a sample space of coin flips

(def sample-space #{:heads :tails})

; > Storing elements in hashtag curly brackets creates a Clojure set. A Clojure set is a collection of unique, unordered elements.

; Suppose we choose an element of `sample-space` at random. What fraction of the time will the chosen element equal Heads? Well, our sample space holds two possible elements. Each element occupies an equal fraction of the space within the set.

; Therefore, we expect `:heads` to be selected with a frequency of `1/2`. That frequency is formally defined as the probability of an outcome. All outcomes within sample_space share an identical probability, which is equal to `(/ 1 (count sample-space))`.


; ##### 1.2 Computing a probablity of heads
(def probability-heads
  (/ 1 (count sample-space)))

; Probability of choosing `:heads` is 0.5. This relates directly to the action of flipping a coin. We’ll assume the coin is unbiased, which means the coin is equally likely to fall on either heads or tails. Thus, a coin flip is conceptually equivalent to choosing a random element from sample_space. The probability of the coin landing on heads is therefore 0.5; the probability of it landing on tails is also equal to 0.5.

; We’ve assigned probabilities to our two measurable outcomes. However, there are additional questions we could ask.

; > What is the probability that the coin lands on either heads or tails?

; Or, more exotically,

; > What is the probability that the coin will spin forever in the air, landing on neither heads nor tails?

; To find rigorous answers, we need to define the concept of an event.

; An **event** is the subset of those elements within sample_space that satisfy some event condition (as shown in figure 1.1). An event condition is a simple Boolean function whose input is a single sample_space element. The function returns True only if the element satisfies our condition constraints.


;; IMAGE GOES HERE



;; ##### 1.3 Defining event conditions

; Let’s define two event conditions:
; - one where the coin lands on either heads or tails, and
; - another where the coin lands on neither heads nor tails.

(defn head-or-tail? [outcome]
  (sample-space outcome))

(defn neither? [outcome]
  (not (head-or-tail? outcome)))

; Also, for the sake of completeness, let’s define event conditions for the two basic events in which the coin satisfies exactly one of our two potential outcomes.

(defn head? [outcome]
  (= outcome :heads))

(defn tail? [outcome]
  (= outcome :tails))


;; ---
;; ##### 1.4 Identifying events using event conditions

;; We can pass event conditions into a generalized `get-matching-event` function.

;; That function is defined below. Its inputs are an event condition and a generic sample space.

;; The function iterates through the generic sample space using `for comprension macro` and returns the set of outcomes where `event-condition` (outcome) is `true`.
(defn get-matching-event
  [event-condition sample-space]
  (into #{} (for [sample sample-space
                  :when (event-condition sample)]
                sample)))

;; Let’s execute `get-matching-event` on our four event conditions. Then we’ll output the four extracted events.

(for [event-condition [head? tail? head-or-tail? neither?]]
 (get-matching-event event-condition sample-space))

;; We’ve successfully extracted four events from sample_space.

; > What is the probability of each event occurring?

; Earlier, we showed that the probability of a single-element outcome for a fair coin is `(/ 1 (count sample_space))`. This property can be *generalized* to include multi-element events.

; The probability of an event is equal to `(/ (count event) (count sample-space))`, but only if all outcomes are known to occur with equal likelihood. In other words, the probability of a multi-element event for a fair coin is equal to the event size divided by the sample space size. We now use event size to compute the four event probabilities.

; ---
; ##### 1.5 Computing event probabilities
(defn compute-probability [event-condition sample-space]
  (let [event (get-matching-event event-condition sample-space)]
    (/ (count event) (count sample-space))))

;; The `compute-probability` function:
;; 1. identifies the event associated with an inputted event condition using `get-matching-event` function
;; 2. it uses the event to compute its probability. `(/ (count event) (count sample-space))`

;; Its time to test our function we wil save it in result var
(def result (for [event-condition [head? tail? head-or-tail? neither?]]
              {:matching-event (get-matching-event event-condition sample-space)
               :probability (compute-probability event-condition sample-space)}))

;; We can use the `table` function from `clerk` namespace to easily visualize the results.
;; All we need to do is pass the result as the first argument

(clerk/table result)

;; As we can see from the table that the smallest probablity is 0 and the largest is 1.0 representing the lower and upper bounds of probability.

;; ---

;; ### 1.1.1 Analysing a biased coin

;; > We computed probabilities for an unbiased coin. What would happen if that coin was biased? Suppose, for instance, that a coin is four times more likely to land on heads relative to tails. How do we compute the likelihoods of outcomes that are not weighted in an equal manner?

;; We can create a weighted sample space represented by a Clojure map.

;; Each outcome is treated as a key whose value maps to the associated weight. In our example, `:heads` is weighted four times as heavily as `:tails`, so we map `:tails` to `1` and `:heads` to `4`.

;; ##### 1.6 Defining a weighted sample space

(def weighted-sample-space {:heads 4 :tails 1})

;; Our new sample space is stored in a map. This allows us to redefine the size of the sample space as the sum of all map weights. Within `weighted-sample-space`, that sum will equal 5.
(comment
  (get-matching-event head-or-tail? weighted-sample-space)
  (get-matching-event head-or-tail? sample-space))
