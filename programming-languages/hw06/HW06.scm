#lang eopl
;---------------------------------------------------------------------
;
;    FILE NAME:    HW06.rkt
;
;    DESCRIPTION:  arith_scanparse.scm, arith_eval.scm
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      03/28/2016 - started
;                  03/29/2016 - continued work
;                  03/30/2016 - completed
;                  04/04/2016 - refactored variables & submitted
;
;    NOTES:	Program fails when given negative numbers
;                  read-eval-print not functioning properly
;
;---------------------------------------------------------------------

; arith_scanparse.scm
; ************************************************************
; SCANNER SPECIFICATIONS

; BNF:
;   <scanner-spec>      ::== ( {<regexp-and-action>}* )
;   <regexp-and-action> ::== (<name> ( {<regexp>}* ) <outcome>)
;   <name>              ::== <symbol>
;   <regexp>            ::== <string> | letter | digit | whitespace | any
;                       |    (not <character> ) | (or {<regexp>}* )
;                       |    (arbno <regexp> ) | (concat {<regexp>}* )
;   <outcome>           ::== skip | symbol | number | string

; ARITH-SCANNER:
(define arith-scanner-spec
  '(
    ; format: NAME REGEXP OUTCOME
    ; white-sp   is whitespace                                    - skip it
    (white-sp   (whitespace)                                    skip  )
    ; comment    is % followed by any non-newline chars(s)        - skip (or save) it
    (comment    ("%"     (arbno (not #\newline)))               skip)
    ; identifier is letter followed by letter(s) and/or digit(s)  - return symbol
    (identifier (letter  (arbno (or letter digit)))             symbol)
    ; operator is a symbol matching: +, -, *, or /                - return symbol
    (operator   ("+")                                           symbol)
    (operator   ("-")                                           symbol)
    (operator   ("*")                                           symbol)
    (operator   ("/")                                           symbol)
    ; number     is digit followed by digit(s)                    - return number
    (number     (digit   (arbno digit))                         number)
    ))

; ************************************************************
; PARSER SPECIFICATIONS

; BNF
;   <grammar>       ::== ( {<production>}* )
;   <production>    ::== ( <lhs> ( {<rhs-item>}* ) <prod-name> )
;   <lhs>           ::== <symbol>
;   <rhs-item>      ::== <symbol> | <string> | (arbno {<rhs-item>}* )
;                   |    ( separated-list {<rhs-item>}* <string> )
;   <prod-name>     ::== <symbol>
; - separated-list is convenient for defining lists of things with separators

; ARITH-PARSER
; BNF
;   <arith-expr> ::== <arith-term> { <add-op> <arith-term> }*
;   <arith-term> ::== <arith-factor> { <mult-op> <arith-factor> }*
;   <arith-factor> ::== <number>
;   <arith-factor> ::== ( <arith-expr> )
;   <add-op> ::== + | -
;   <mult-op> ::== * | / 
(define arith-grammar
  '(
    ; format: LHS RHS NAME
    (arith-expr    (arith-term   (arbno add-op arith-term))     add-expr)
    (arith-term    (arith-factor (arbno mult-op arith-factor))  mult-expr)
    (arith-factor  (number)                                     num-factor)
    (arith-factor  ( "(" arith-expr ")")                        inner-factor)
    (add-op        ("+")                                        sum-op)
    (add-op        ("-")                                        sub-op)
    (mult-op       ("*")                                        times-op)
    (mult-op       ("/")                                        div-op)
    ))

; ************************************************************
; USING SCANNER & PARSER SPECIFICATIONS

; make-define-datatypes - make define-datatype definitions for scanner & parser
(sllgen:make-define-datatypes arith-scanner-spec arith-grammar)

; list-define-datatypes - lists datatypes for scanner & parser
(define list-the-datatypes
  (lambda () (sllgen:list-define-datatypes arith-scanner-spec arith-grammar)) )

; make-string-scanner - make scanner that reads string, returns list of token/value/lineno triples
(define just-scan
  (sllgen:make-string-scanner arith-scanner-spec '()) )

; make-string-parser - make parser that reads string, returns syntax tree
(define scan&parse
  (sllgen:make-string-parser arith-scanner-spec arith-grammar) )

; arith_eval
; ************************************************************
; EVALUATOR

(define arith-expr-evaluator
  (lambda (expr)
    (cases arith-expr expr
      (add-expr  (terms1 op-list terms2)
                   (add-expr-evaluator    (arith-term-evaluator terms1)
                                          op-list
                                          terms2)
    ))))

(define arith-term-evaluator
  (lambda (term)
    (cases arith-term term
      (mult-expr (factors1 op-list factors2)
                   (mult-expr-evaluator   (arith-factor-evaluator factors1)
                                          op-list
                                          factors2)
    ))))

; add-expr-evaluator
;   helper function for arith-expr-evaluator
(define add-expr-evaluator
  (lambda (value op-list term-list)
    (cond
      [(null? op-list) value]
      [#t (add-expr-evaluator ((add-op-evaluator (car op-list))
                                  value
                                  (arith-term-evaluator   (car term-list)))
                              (cdr op-list)
                              (cdr term-list))]
    )))

; mult-expr-evaluator
;   helper function for arith-term-evaluator
(define mult-expr-evaluator
  (lambda (value op-list factor-list)
    (cond
      [(null? op-list) value]
      [#t (mult-expr-evaluator ((mult-op-evaluator (car op-list))
                                  value
                                  (arith-factor-evaluator (car factor-list)))
                               (cdr op-list)
                               (cdr factor-list))]
    )))

(define arith-factor-evaluator
  (lambda (af)
    (cases arith-factor af
      (num-factor      (val)                             val)
      (inner-factor    (expr)    (arith-expr-evaluator expr))
    )))

(define add-op-evaluator
  (lambda (aop)
    (cases add-op aop
      (sum-op    ()    +)
      (sub-op    ()    -)
    )))

(define mult-op-evaluator
  (lambda (mop)
    (cases mult-op mop
      (times-op    ()    *)
      (div-op      ()    /)
    )))



(define scan&parse&eval
  (lambda (s) (arith-expr-evaluator (scan&parse s))) )

; make-stream-parser - make parser that reads stream
; make-rep-loop      - make read-eval-print loop
; evaluator must be defined by user
(define read-eval-print
  (sllgen:make-rep-loop "--> " arith-expr-evaluator
                        (sllgen:make-stream-parser arith-scanner-spec arith-grammar) ))

; examples:
(define data0         "3*3*3")  
(define data1       "1+2*3")
(define data2   "  (1+2)*3")
(define data3 "(1+2)*(1+1)")
(define data4  "3 + 2*66-5")

;(define data5      "3+(-3)") ;FAIL
;(define data6        "-3+3") ;FAIL
 
;(display (just-scan data0))
;(display (just-scan data1))
;(display (just-scan data2))
;(display (just-scan data3))
;(display (just-scan data4))

;(display (scan&parse data0))
;(display (scan&parse data1))
;(display (scan&parse data2))
;(display (scan&parse data3))
;(display (scan&parse data4))

;(display (scan&parse&eval data0))    ;expect 27
;(display (scan&parse&eval data1))    ;expect 7
;(display (scan&parse&eval data2))    ;expect 9
;(display (scan&parse&eval data3))    ;expect 6
;(display (scan&parse&eval data4))    ;expect 130

(display (arith-expr-evaluator (scan&parse data2)))    ;expect 9
;(read-eval-print)