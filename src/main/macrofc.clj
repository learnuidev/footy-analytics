(ns macrofc)


;; Macro Mastery - Patterns Approach -
;; There are three ways to write macros
;; 1 - list function (reverse-macro-v1)
;; 2 - ` ~  pattern
;; Use Case:
;; - useful when passing arguments to the macro
;; 3 - ` ~@ pattern
;; - useful when getting the first item from the list

;;
(comment
  (clojure.repl/doc defmacro))

;; Lets improve our macro
(defmacro reverse-macro-v1 [args]
  (list reverse args))

(defmacro reverse-macro-v2 [args]
  `(reverse ~args))

(defmacro reverse-macro-v3 [args]
  `(reverse ~@args))

(defmacro reverse-macro [test & args]
  `(reverse (identity ~@args)))

(defmacro conj-macro [arg]
  `(conj ~arg 1))

(comment
  (conj-macro [3]))

(comment
  (conj [] 1))


(comment
  (= (reverse-macro-v1 "Luis Diaz")
     (reverse-macro-v2 "Luis Diaz"))
  (reverse-macro 1 "Luis Diaz"))

;; macroexpand
(comment
  (clojure.repl/doc macroexpand)
  (macroexpand '(reverse-macro "Luiz Diaz"))
  (type (macroexpand '(reverse-macro "Luiz Diaz")))
  (macroexpand '(reverse-macro "Luiz Diaz"))
  (eval (macroexpand '(reverse-macro "Luiz Diaz"))))
