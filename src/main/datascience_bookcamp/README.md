# Data Science Bookcamp: Five Python Projets Notes

## Computing Probabilities in Clojure and Python (libpython-clj)

Learning Objects
- What is the basics of Probability Theory?
- Computing probabilities of a single observation
- Computing probabilities across a range of observations


### 1.1 Sample space analysis: An equation-free approach for measuring uncertainty in outcomes

- A sample space is the set of all the possible outcomes an action could produce.
- Let’s take the simple action of flipping a coin. The coin will land on either heads or tails. Thus, the coin flip will produce one of two measurable outcomes: heads or tails. By storing these outcomes in a Clojure set, we can create a sample space of coin flips.

```clj
(def sample-space #{:heads :tails})
```

Storing elements in hashtag curly brackets creates a Clojure set. A Clojure set is a collection of unique, unordered elements.
