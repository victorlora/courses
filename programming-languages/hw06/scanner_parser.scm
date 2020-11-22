#lang eopl

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

; EXAMPLE:
(define scanner-spec-a
  '(
    ; format: NAME REGEXP OUTCOME
    ; white-sp   is whitespace                                    - skip it
    (white-sp   (whitespace)                                    skip  )
    ; comment    is % followed by any non-newline chars(s)        - skip (or save) it
    (comment    ("%"     (arbno (not #\newline)))               string)
    ; identifier is letter followed by letter(s) and/or digit(s)  - return symbol
    (identifier (letter  (arbno (or letter digit)))             symbol)
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

; EXAMPLE
(define grammar-a
  '(
    ; format: LHS RHS NAME
    ; compound-statement is two statements in {} separated by ;
    (statement  ("{" statement ";" statement "}")       compound-statement)
    (statement  ("while" expression "do" statement)        while-statement)
    (statement  (identifier ":=" expression)              assign-statement)
    (expression (identifier)                            var-exp)
    (expression (number)                                num-exp)
    (expression ("(" expression "+" expression ")")     sum-exp)
    ))

; ************************************************************
; USING SCANNER & PARSER SPECIFICATIONS

; make-define-datatypes - make define-datatype definitions for scanner & parser
(sllgen:make-define-datatypes scanner-spec-a grammar-a)

; list-define-datatypes - lists datatypes for scanner & parser
(define list-the-datatypes
  (lambda () (sllgen:list-define-datatypes scanner-spec-a grammar-a)) )

; make-string-scanner - make scanner that reads string, returns list of token/value/lineno triples
(define just-scan
  (sllgen:make-string-scanner scanner-spec-a '()) )

; make-string-parser - make parser that reads string, returns syntax tree
(define scan&parse
  (sllgen:make-string-parser scanner-spec-a grammar-a) )

(define stmt-evaluator
  (lambda (stmt)
    (cases statement stmt
      (compound-statement (stmt1 stmt2)
                          (list "compound"    (stmt-evaluator stmt1)
                                              (stmt-evaluator stmt2)) )
      (while-statement    (expr1 stmt1)
                          (list "while"       (expr-evaluator expr1)
                                              (stmt-evaluator stmt1)) )
      (assign-statement   (id1 expr1)
                          (list "assign" id1  (expr-evaluator expr1)) )
      )))


(define expr-evaluator
  (lambda (expr)
    (cases expression expr
      (var-exp  (sym1)        (list "var" sym1))
      (num-exp  (val)         (list "num"  val))
      (sum-exp  (expr1 expr2) (list "sum" (expr-evaluator expr1) (expr-evaluator expr2) ))
      )))

(define scan&parse&eval
  (lambda (s) (stmt-evaluator (scan&parse s))) )

; make-stream-parser - make parser that reads stream
; make-rep-loop      - make read-eval-print loop
; evaluator must be defined by user
(define read-eval-print
  (sllgen:make-rep-loop "--> " stmt-evaluator
                        (sllgen:make-stream-parser scanner-spec-a grammar-a) ))

; examples:
(define data0 "    b a45jhdg asdfs 4 456456 % this is a comment")
(define data1 "while x do y := z")
(define data2 "{ while x do y := z ; z := y }")
;(just-scan data1)
;(scan&parse data1)
;(stmt-evaluator (scan&parse data2))
;(scan&parse&eval data2)
;(read-eval-print)
