#lang eopl
;---------------------------------------------------------------------
;
;    FILE NAME:    HW07.rkt
;
;    DESCRIPTION:  environments.scm
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      04/06/2016 - started
;                  04/07/2016 - continued & finished
;
;    NOTES:	Rewrite the 3 environment ADT implementations
;                  (procedural, datatype, and ribcage) so that instead
;                  of using a list of symbols and a list of values, they
;                  use a list of symbol-value pairs. So the environment
;                  might be created by code like either of the following
;                  lines (you can choose which one).
;
;---------------------------------------------------------------------

; INTERFACE for the ENVIRONMENT (aka SYMBOL TABLE)
; - maps a finite set of symbols to an associated set of values
;   e.g. names of variables/procedures/objects to their values/definitions
; - this ADT (and various implementations) will be very useful later...
; - want to be able to represent all sets of the form
;   { (s1,v1), ... , (s2,v2) }
;
; - to do this INDUCTIVELY requires 3 operations:
;   1. the empty environment
;   (empty-env)                                => <empty-env>
;   2. add symbols and values to old environment to get new environment
;   (extend-env '(<s1>, ... ,<sk>)
;               '(<v1>, ... ,<vk>) <old-env> ) => <new-env>
;   3. evaluate s in environment env
;   (apply-env <env> <sym>)   => env(sym)      => val
;
; - empty-env and extend-env are CONSTRUCTORS
; - apply-env is OBSERVER

; the INTERFACE can be IMPLEMENTED in at least 3 ways...

; helper procedures
(define list-find-position
  (lambda (sym los) (list-index (lambda (sym1) (eqv? sym1 sym)) los)))
(define list-index
  (lambda (pred ls)
    (cond
      ((null? ls) #f)
      ((pred (caar ls)) 0)
      (else (let ((list-index-r (list-index pred (cdr ls))))
              (if (number? list-index-r)
                  (+ list-index-r 1)
                  #f
                  ))))))

; APPROACH #1: PROCEDURAL representation
; chain of procedures with map lists
(define empty-env1
  (lambda () (lambda (sym) (eopl:error 'apply-env "No binding for ~s" sym) )))

(define extend-env1
  (lambda (map env)
    (lambda (sym)
      (let ((pos (list-find-position sym map)))
        (if (number? pos)
            (cadr (list-ref map pos))
            (apply-env1 env sym) )))))

(define apply-env1 (lambda (env sym) (env sym)))

; ============================================================
; TEST DATA (for APPROACH #1)
(define env1 (extend-env1 '((c 3) (d 4))
                 (extend-env1 '((a 1) (b 2)) (empty-env1))))
(display (apply-env1 env1 'a))
;(display (apply-env1 env1 'z))

; ============================================================
; APPROACH #2: ABSTRACT SYNTAX TREE representation
; nested ADT with sym & value map list, using define-datatype

(define-datatype env env?
  (empty-env-record)   ; env can be empty
  (extended-env-record ; env can have lists of syms & vals, and prev env
   (map     (list-of pair?))
   (prev-env env?)
   ))

; helper procedures

(define  empty-env2    (lambda ()
                          (empty-env-record)))
(define extend-env2    (lambda (map prev-env)
                          (extended-env-record map prev-env)))

(define apply-env2
  (lambda (this-env sym)
    (cases env this-env
      (empty-env-record
       ()
       (eopl:error 'apply-env2 "No binding for ~s" sym))
      (extended-env-record
       (map prev-env)
       (let ((pos (list-find-position sym map)))
         (if (number? pos)
             (cadr (list-ref map pos))
             (apply-env2 prev-env sym) ))))))

; ============================================================
; TEST DATA (for APPROACH #2)
(define env2 (extend-env2 '((c 3) (d 4))
                 (extend-env2 '((a 1) (b 2)) (empty-env2))))
(display (apply-env2 env2 'b))
;(display (apply-env2 env2 'z))


; ============================================================
; APPROACH #3: RIB-CAGE
; linked list of linked lists

; helper procedures
(define rib-find-position list-find-position)

(define  empty-env3 (lambda () '()) )

(define extend-env3 (lambda (pair env) (append pair env) ))

(define apply-env3
  (lambda (env sym)
    (if (null? env)
        (eopl:error 'apply-env "No binding for ~s" sym)
        (let ((map        (car env)))
          (let ((pos (rib-find-position sym env)))
            (if (number? pos)
                (cadr (list-ref env pos))
                (apply-env3 (cdr env) sym)
                )) ))))

; ============================================================
; TEST DATA (for APPROACH #3)
(define env3 (extend-env3 '((c 3) (d 4))
                 (extend-env3 '((a 1) (b 2)) (empty-env3))))
(display (apply-env3 env3 'c))
;(display (apply-env3 env3 'z))