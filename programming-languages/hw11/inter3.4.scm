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
    ; (3.3) conditional
    (expression ("if" expression "then" expression "else" expression)
                if-exp)
    ; (3.4) local binding
    (expression ("let" (arbno  identifier "=" expression) "in" expression)
                let-exp)
    
    ; (3.1) primitives
    (primitive  ("+")      add-prim)
    (primitive  ("-")      subtract-prim)
    (primitive  ("*")      mult-prim)
    (primitive  ("add1")    incr-prim)
    (primitive  ("sub1")    decr-prim)
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
      (add-prim      ()  (+ (car args) (cadr args)))
      (subtract-prim  ()  (- (car args) (cadr args)))
      (mult-prim      ()  (* (car args) (cadr args)))
      (incr-prim      ()  (+ (car args) 1))
      (decr-prim      ()  (- (car args) 1)) )))

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
  (lamda (s) (eval-program(scan&parse s))) )

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
(scan&parse test-3.3a)
(scan&parse test-3.4a)