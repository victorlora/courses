#lang eopl
;---------------------------------------------------------------------
;
;    FILE NAME:    HW11.scm
;
;    DESCRIPTION:  environments.scm
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      04/14/2016 - started
;                  04/15/2016 - continued & completed
;
;    NOTES:        Extend interp3.4.scm (available from Moodle) to include
;                  the following capabilities:
;                      - Add list processing primitives (ex. 3.7, pg 79),
;                        including cons, car, cdr, list, and a defined constant
;                        emptylist.
;                      - Handle variable numbers of parameters (ex 3.9, pg 79).
;                        In particular, "add1" and "sub1" should have exactly 1
;                        parameter, "-" should have exactly 2 parameters, and "+"
;                        and "*" should have 2 or more parameters.
;                  Whenever possible, these issues should be addressed at compile
;                  time (in the scanner and parser), not at run time (in the evaluator).
;                      - Add cond expressions (ex 3.13, pg 81). You can refer to the if-then-else
;                        expression on page 80 and in interp3.9.scm.
;---------------------------------------------------------------------

; 3.4 INTERPRETER
; - this interpreter consists of several components
;  - SCANNER    - converts characters to tokens
;  - PARSER      - converts tokens to abstract syntax trees
;  - ENVIRONMENT - maps identifiers to values
;  - EVALUATOR  - converts abstract syntax trees to values/results
; - this interpreter supports:
;  - (3.1) basic functionality
;    - whitespace & comments (skipped)
;    - integers & built-in defined constants
;    - 3 binary primitive operations - add, sub, mult
;    - 2  unary primitive operations - incr, decr
;  - (3.3) conditionals  - if-then-else
;  - (3.4) local bindings - let 
;
; - things to do (save as 3.4a)
;

; ------------------------------------------------------------
; scanner specification

(define scanner-spec
  '(
    (whitespace (whitespace)                          skip)
    (comment    ("%" (arbno (not #\newline)))          skip)
    (identifier (letter (arbno (or letter digit "?"))) symbol)
    (number    (digit (arbno digit))                  number) ))

; ------------------------------------------------------------
; grammar specification

(define grammar
  '(
    ; (3.1) program
    (program    (expression)
                a-program)
    ; (3.1) expressions
    (expression (number)
                lit-exp)
    (expression (identifier)
                var-exp)
    (expression (primitive "(" (separated-list expression ",") ")" )  
                primapp-exp)
    (expression (prim-one "(" expression ")")
                primapp-expr-one)
    (expression (prim-two "(" expression "," expression ")")
                primapp-expr-two)
    ; (3.3) conditional
    (expression ("if" expression "then" expression "else" expression)
                if-exp)
    ; (3.4) local binding
    (expression ("let" (arbno  identifier "=" expression) "in" expression)
                let-exp)
    (expression ("car" "(" expression ")") car-expr)
    (expression ("cdr" "(" expression ")") cdr-expr)

    ; cons expression
    (expression ("cond" (arbno expression "==>" expression) "end")
                cond-exp)

    ; emptyList
    (expression ("emptylist") empty-lst)

    ; (3.1) primitives
    (primitive  ("+")       add-prim)
    (prim-two   ("-")       subtract-prim)
    (primitive  ("*")       mult-prim)
    (prim-one   ("add1")    incr-prim)
    (prim-one   ("sub1")    decr-prim)
    ;(primitive  ("car")     car-prim)
    ;(primitive  ("cdr")     cdr-prim)
    (primitive  ("list")    list-prim)
    (primitive  ("cons")    cons-prim)
    
    ))

; ------------------------------------------------------------
; define datatypes before defining interpreter

(sllgen:make-define-datatypes scanner-spec grammar)
(define dump-datatypes
  (lambda () (sllgen:list-define-datatypes scanner-spec grammar)))

; ------------------------------------------------------------
;  environment (using ribcage implementation - see 2.3.4)
; - maps identifiers to values

(define-datatype environment environment?
  (empty-env-record)
  (extended-env-record
    (syms (list-of symbol?))
    (vec  vector?)              ; can use this for anything.
    (env  environment?))
  )

(define empty-env
  (lambda ()
    (empty-env-record)))

(define extend-env
  (lambda (syms vals env)
    (extended-env-record syms (list->vector vals) env)))

(define apply-env
  (lambda (env sym)
    (cases environment env
      (empty-env-record ()
        (eopl:error 'apply-env "No binding for ~s" sym))
      (extended-env-record (syms vals env)
        (let ((position (rib-find-position sym syms)))
          (if (number? position)
              (vector-ref vals position)
              (apply-env env sym)))))))

(define rib-find-position 
  (lambda (sym los)
    (list-find-position sym los)))

(define list-find-position
  (lambda (sym los)
    (list-index (lambda (sym1) (eqv? sym1 sym)) los)))

(define list-index
  (lambda (pred ls)
    (cond
      ((null? ls) #f)
      ((pred (car ls)) 0)
      (else (let ((list-index-r (list-index pred (cdr ls))))
              (if (number? list-index-r)
                (+ list-index-r 1)
                #f))))))

; ------------------------------------------------------------
;  evaluator

; evaluate program
(define eval-program
  (lambda (pgm)
    (cases program pgm
      (a-program      (body)      (eval-expression body (init-env)) ))))

; evaluate expression
(define eval-expression
  (lambda (exp env)
    (cases expression exp
      ; (3.1) literals, variables, primitive applications
      (lit-exp        (datum)    datum)
      (var-exp        (id)        (apply-env env id))
      (primapp-exp    (prim rands)
                      (let ((args (eval-rands rands env)))
                        (apply-primitive prim args) ))
      ; (3.3) conditional
      (if-exp        (test-exp true-exp false-exp)
        (if (true-value? (eval-expression test-exp env))
          (eval-expression true-exp env)
          (eval-expression false-exp env)))
      ; (3.4) local binding
      (let-exp        (ids rands body)
        (let ((args (eval-rands rands env)))
          (eval-expression body (extend-env ids args env))))
      ; cond expr
      (cond-exp       (conditions return)
        (eval-cond conditions return env))
      ; primapp-expr-one
      (primapp-expr-one (prim rands)
                        (let ((args (eval-rand rands env)))
                          (apply-prim-one prim args)))
      ; primapp-expr-two
      (primapp-expr-two (prim rand1 rand2)
                        (apply-prim-two prim (eval-rand rand1 env) (eval-rand rand2 env)))
      (empty-lst () '())
      (car-expr (args) (car (eval-expression args env)))
      (cdr-expr (args) (cdr (eval-expression args env)))
      )))

; (3.3) 0=false, anything else is true
; - placeholder for other definitions of true & false
(define true-value?
  (lambda (x) (not (zero? x))))

; (3.4) evaluate operands for a procedure call
(define eval-rands
  (lambda (rands env) (map (lambda (x) (eval-rand x env)) rands) ))
(define eval-rand
  (lambda (rand env) (eval-expression rand env) ))

; (3.1) apply primitive procedure to arguments
(define apply-primitive
  (lambda (prim args)
    (cases primitive prim
      (add-prim      ()  (eval-sum args))
      ;(subtract-prim  ()  (- (car args) (cadr args)))
      (mult-prim      ()  (eval-prod args))
      ;(incr-prim      ()  (+ (car args) 1))
      ;(decr-prim      ()  (- (car args) 1))
      ;(car-prim       ()  (caar args))
      ;(cdr-prim       ()  (cdr (car args)))
      (list-prim      ()  args)
      (cons-prim      ()  (cons (car args) (cadr args)))
      )))

; apply primitive one procedures to argument
(define apply-prim-one
  (lambda (prim arg)
    (cases prim-one prim
      (incr-prim () (+ arg 1))
      (decr-prim () (- arg 1))
      )))

; apply primitive two procedure to arguments 1 and 2
(define apply-prim-two
  (lambda (prim arg1 arg2)
    (cases prim-two prim
      (subtract-prim () (- arg1 arg2))
      )))
; eval-sum
(define eval-sum
  (lambda (args)
    (if (equal? (length args) 0)
        (eopl:error "Error: cannot apply addition operation on an empty set")
        (if (equal? (length args) 1)
            (car args)
            (+ (car args) (eval-sum (cdr args)))
            )
        )))

; eval-prod
(define eval-prod
  (lambda (args)
    (if (equal? (length args) 0)
        (eopl:error "Error: cannot apply multiplication operation on an empty set")
        (if (equal? (length args) 1)
            (car args)
            (* (car args) (eval-prod (cdr args)))
            )
        )))

; eval-cond
(define eval-cond
  (lambda (condition-lst return-lst env)
    (if (null? condition-lst)
        0
        (if (true-value? (eval-expression (car condition-lst) env))
            (eval-expression (car return-lst) env)
            (eval-cond (cdr condition-lst) (cdr return-lst) env))
        )))

; initial environment (named constants only, since it can't be changed)
(define init-env
  (lambda () (extend-env '(i v x) '(1 5 10) (empty-env)) ))

; ------------------------------------------------------------
;  interpreter

(define scan
  (sllgen:make-string-scanner scanner-spec 
                              grammar))
(define scan&parse
  (sllgen:make-string-parser  scanner-spec 
                              grammar))
(define read-dump
  (sllgen:make-rep-loop "--> " (lambda (tree) tree)
                        (sllgen:make-stream-parser scanner-spec
                                                  grammar)))

(define scan&parse&eval
  (lambda (s) (eval-program(scan&parse s))) )

(define read-eval-print
  (sllgen:make-rep-loop "--> " eval-program
                        (sllgen:make-stream-parser scanner-spec 
                                                  grammar)))

; ------------------------------------------------------------
; testing - use (scan), (scan&parse), (read-dump), (read-eval-print) 

(define test-3.1a "add1(2)")
; ignores extra parameters
(define test-3.1b "add1(2,3)")
(define test-3.1c "+(3,4,5)")
; more tests
(define test-3.1d "+(add1(2),-(6,4))")
(define test-3.1e "*(2,+(3,sub1(4)))")
(define test-3.3a "if -(3,+(1,2)) then 2 else 3")
(define test-3.4a "let x = 5 y = 6 in +(x,y)")

;hw11 tests
(define car-test "car(list(2,3,4))")
(define cdr-test "cdr(list(2,3,4))")
(define cons-test "cons(2,3)")
(define cons-test2 "cons(emptylist, cdr(list(1,2,3)))")
(define list-test "list(1,2,3,4,5)")
(define sumlist-test "+(2,3,4)")
(define prodlist-test "*(3,3,3)")
(define sub-test "-(3,2)")
(define add1-test "add1(2)")
(define sub1-test "sub1(3)")
(define cond-test "cond 0 ==> 9 1 ==> 8 2 ==> 3 3 ==> 2 end")


(scan&parse test-3.3a)
(scan&parse test-3.4a)
;(scan&parse "car(4,5,6)")
(scan&parse&eval "+(2,3,4)")

;hw11 test evals
(display (scan&parse&eval car-test))     ;should return 2
(display (scan&parse&eval cdr-test))      ;should return (3 4)
(display (scan&parse&eval cons-test))     ;should return (2 . 3)
(display (scan&parse&eval cons-test2))    ;should return (() 2 3)
(display (scan&parse&eval list-test))     ;should return (1 2 3 4 5)
(display (scan&parse&eval sumlist-test))  ;should return 9
(display (scan&parse&eval prodlist-test)) ;should return 27
(display (scan&parse&eval sub-test))      ;should return 1
(display (scan&parse&eval add1-test))     ;should return 3
(display (scan&parse&eval sub1-test))     ;should return 2
(display (scan&parse&eval cond-test))     ;should return 8

;(read-eval-print)