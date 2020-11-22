#lang eopl

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

; ============================================================
; TEST DATA (for any IMPLEMENTATION)
(define env1 (extend-env '(c d) '(3 4)
                (extend-env '(a b) '(1 2)
                    (empty-env))))
(apply-env env1 'd)
(apply-env env1 'b)
;(apply-env env1 'z)

; ============================================================
; APPROACH #1: PROCEDURAL representation
; chain of procedures with sym & value lists

; helper procedures
(define list-find-position
  (lambda (sym los) (list-index (lambda (sym1) (eqv? sym1 sym)) los)))
(define list-index
  (lambda (pred ls)
    (cond
      ((null? ls) #f)
      ((pred (car ls)) 0)
      (else (let ((list-index-r (list-index pred (cdr ls))))
              (if (number? list-index-r)
                  (+ list-index-r 1)
                  #f
                  ))))))

(define empty-env
  (lambda () (lambda (sym) (eopl:error 'apply-env "No binding for ~s" sym) )))

(define extend-env
  (lambda (syms vals env)
    (lambda (sym)
      (let ((pos (list-find-position sym syms)))
        (if (number? pos)
            (list-ref vals pos)
            (apply-env env sym) )))))

(define apply-env (lambda (env sym) (env sym)))

; ============================================================
; APPROACH #2: ABSTRACT SYNTAX TREE representation
; nested ADT with sym & value lists, using define-datatype

(define-datatype env env?
  (empty-env-record)   ; env can be empty
  (extended-env-record ; env can have lists of syms & vals, and prev env
   (syms     (list-of symbol?))
   (vals     (list-of scheme-value?))
   (prev-env env?)
   ))

; helper procedures
(define scheme-value? (lambda (v) #t))

(define  empty-env    (lambda ()
                          (empty-env-record)))
(define extend-env    (lambda (syms vals prev-env)
                          (extended-env-record syms vals prev-env)))

(define apply-env
  (lambda (this-env sym)
    (cases env this-env
      (empty-env-record
       ()
       (eopl:error 'apply-env "No binding for ~s" sym))
      (extended-env-record
       (syms vals prev-env)
       (let ((pos (list-find-position sym syms)))
         (if (number? pos)
             (list-ref vals pos)
             (apply-env prev-env sym) ))))))


; ============================================================
; APPROACH #3: RIB-CAGE
; linked list of linked lists

; helper procedures
(define rib-find-position list-find-position)

(define  empty-env (lambda () '()) )

(define extend-env (lambda (syms vals env) (cons (list syms vals) env) ))

(define apply-env
  (lambda (env sym)
    (if (null? env)
        (eopl:error 'apply-env "No binding for ~s" sym)
        (let ((syms (car  (car env)))
              (vals (cadr (car env)))
              (env        (cdr env) )
              )
          (let ((pos (rib-find-position sym syms)))
            (if (number? pos)
                (list-ref  vals pos)
                (apply-env env sym)
                )) ))))
