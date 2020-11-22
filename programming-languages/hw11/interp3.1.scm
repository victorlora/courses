; 3.1 INTERPRETER
; - this interpreter consists of several components:
; - SCANNER - converts characters to tokens
; - PARSER - converts tokens to abstract syntax trees
; - ENVIRONMENT - maps identifiers to values
; - EVALUATOR - converts abstract syntax trees to values/results
; - this interpreter supports:
; - whitespace & comments (skipped)
; - built-in defined constants
; - integers
; - 3 binary primitive operations - add, sub, mult
; - 2 unary primitive operations - incr, decr

; ------------------------------------------------------------
; scanner specification
; - anything not recognized by scanner is passed to parser
; (e.g. "(" ")" ",")

(define scanner-spec
  '(
    ; whitespace is whitespace
    (whitespace
     (whitespace) skip)
    ; comments start with % and end with newline
    (comment
     ("%" (arbno (not #\newline))) skip)
    ; identifiers start with letter and contain letters, digits, or ?
    (identifier
     (letter (arbno (or letter digit "?"))) symbol)
    ; numbers consist of digits
    (number
     (digit (arbno digit)) number) 
    ))

; ------------------------------------------------------------
; grammar specification

(define grammar
  '(
    ; programs are expressions
    (program (expression) 
             a-program)
    ; expressions are numbers, identifers,
    ; or primitives applied to a list of operands
    (expression (number)
                lit-exp)
    (expression (identifier)
                var-exp)
    (expression (primitive "(" (separated-list expression ",") ")" )
                primapp-exp)
    ; primitives
    (primitive ("+") add-prim)
    (primitive ("-") subtract-prim)
    (primitive ("*") mult-prim)
    (primitive ("add1") incr-prim)
    (primitive ("sub1") decr-prim)
    ))

; ------------------------------------------------------------
; AST datatypes (defined below by make-define-datatypes from grammar)

; (define-datatype program program?
; (a-program (exp expression?) ))
;
; (define-datatype expression expression?
; (lit-exp (datum number?) )
; (var-exp (id symbol?) )
; (primapp-exp (prim primitive?)
; (rands (list-of expression?)) ) )
;
; (define-datatype primitive primitive?
; (add-prim)
; (subtract-prim)
; (mult-prim)
; (incr-prim)
; (decr-prim) )

; ------------------------------------------------------------
; create datatypes (see above)

(sllgen:make-define-datatypes scanner-spec grammar)
(define dump-datatypes
  (lambda () (sllgen:list-define-datatypes scanner-spec grammar)))

; ------------------------------------------------------------
; environment (using ribcage implementation - see 2.3.4)
; - maps identifiers to values

(define-datatype environment environment?
  (empty-env-record)
  (extended-env-record
   (syms (list-of symbol?))
   (vec vector?) ; can use this for anything
   (env environment?))
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
; evaluator
; - takes abstract syntax tree and returns result
; - uses environment ADT from Chapter 2,
; though grammar doesn't provide any way to modify environment

; evaluate program
(define eval-program
  (lambda (pgm)
    (cases program pgm
      (a-program (body)
                 (eval-expression body (init-env)) ))))

; evaluate expression
(define eval-expression
  (lambda (exp env)
    (cases expression exp
      ; literals, variables, primitive applications 
      (lit-exp (datum) datum)
      (var-exp (id) (apply-env env id))
      (primapp-exp (prim rands)
                   (let ((args (eval-rands rands env)))
                     (apply-primitive prim args) )) )))

; evaluate operands for a procedure call
(define eval-rands
  (lambda (rands env)
    (map (lambda (x) (eval-rand x env)) rands) ))

(define eval-rand
  (lambda (rand env)
    (eval-expression rand env) ))

; apply primitive procedure to parameters
(define apply-primitive
  (lambda (prim args)
    (cases primitive prim
      (add-prim () (+ (car args) (cadr args)))
      (subtract-prim () (- (car args) (cadr args)))
      (mult-prim () (* (car args) (cadr args)))
      (incr-prim () (+ (car args) 1))
      (decr-prim () (- (car args) 1)) )))

; initial environment (named constants only, since it can't be changed)
(define init-env
  (lambda ()
    (extend-env '(i v x) '(1 5 10) (empty-env)) ))

; ------------------------------------------------------------
; interpreter

; scan only (grammer is ignored)
(define scan
  (sllgen:make-string-scanner scanner-spec 
                              grammar))
; scan & parse only
(define scan&parse
  (sllgen:make-string-parser scanner-spec 
                             grammar))
; parser loop that returns root of abstract syntax tree
(define read-dump
  (sllgen:make-rep-loop "--> " (lambda (tree) tree)
                        (sllgen:make-stream-parser scanner-spec 
                                                   grammar)))
; scan & parse & eval
(define scan&parse&eval
  (lambda (s) (eval-program (scan&parse s))) )

; interpreter loop
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
;(scan test-3.1a)
;(scan&parse&eval test-3.1b)
(read-eval-print)