#lang racket
;---------------------------------------------------------------------
;
;    FILE NAME:    HW03.rkt
;
;    DESCRIPTION:  merge, car&cdr, if->cond
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      2/9/2016 - started & finished
;
;    NOTES:	       N/A
;
;---------------------------------Code---------------------------------


;; NAME: merge
;;
;; DESC: A procedure (merge lon1 lon2) where lon1 and lon2 are lists of
;; numbers sorted in ascending order, that returns a sorted list of all
;; of the numbers in lon1 and lon2.
;;
(define merge
  (lambda (lon1 lon2)
    (cond
      [(empty? lon1) lon2]
      [(empty? lon2) lon1]
      [(< (car lon1) (car lon2)) (cons (car lon1) (merge (cdr lon1) lon2))]
      [#t (cons (car lon2) (merge lon1 (cdr lon2)))]
    )
  )
)

;; Test
(merge '(3 5) '())	               ; expect '(3 5)
(merge '() '(4 8))	               ; expect '(4 8)
(merge '(3 5 12 23) '(4 8 9 18))   ; expect '(3 4 5 8 9 12 18 23)
(merge '() '())                    ; expect '()



;; NAME: car&cdr
;;
;; DESC: A procedure (car&cdr s slst errval) that returns
;; the code for a procedure that takes a list with the same
;; structure as slst and returns the value in the same position
;; as the leftmost occurrence of s in slst . If s does not occur
;; in slst then errval is returned.
;;
(define car&cdr
  (lambda (s slst errval)
    (letrec (
             [in-list? (lambda (needle haystack)
                         (cond
                           [(null? haystack) #f]
                           [(eq? needle (car haystack)) #t]
                           [#t (in-list? needle (cdr haystack))]
                           ))])
      (letrec (
               [car&cdr-helper
                (lambda (s slst errval func)
                  (cond
                    [(null? slst) errval]
                    [(eq? s (car slst)) (list 'lambda '(lst) (list 'car func))]
                    [(and (list? (car slst)) (in-list? s (car slst)))
                     (car&cdr-helper s (car slst) errval (list 'car func))]
                    [#t (car&cdr-helper s (cdr slst) errval (list 'cdr func))]
                    ))])
        (cond
          [(null? slst) errval]
          [#t (car&cdr-helper s slst errval 'lst)]
          )))))

;; Test
(car&cdr 'a '(a b c) 'fail)	; expect (lambda (lst) (car lst))
(car&cdr 'c '(a b c) 'fail)	; expect (lambda (lst) (car (cdr (cdr lst))))
(car&cdr 'c '(a b) 'fail)	; expect 'fail
(car&cdr 'dog '(cat lion (fish dog) pig) 'fail)
                ; expect (lambda (lst) (car (cdr (car (cdr (cdr lst))))))



;; NAME: if->cond
;;
;; DESC: A procedure if->cond that takes an if expression and returns the
;; corresponding cond expression.
;;
(define if->cond
  (lambda (lst)
    (cond
      [(not (list? lst)) lst]
      [(eq? (length lst) 3) (list 'cond (list 'else (list 'if (second lst) (third lst))))]
      [#t (cons 'cond (if->cond-helper lst))]
    )))

(define if->cond-helper
  (lambda (lst)
    (cond
      [(not (list? lst)) (list (list 'else lst))]
      [(and (not (list? (caddr lst)))
            (not (list? (cadddr lst))))
       (list (list (second lst) (third lst)) (list 'else (cadddr lst)))]
      [#t
       (cons (cons (second lst) (list (third lst))) (if->cond-helper (cadddr lst)))]
      
    
    )))
  
;; Test
(if->cond '(if a b))	        ; expect (cond (else (if a b)))
(if->cond '(if a b c))	        ; expect (cond (a b) (else c))
(if->cond '(if a b (if c d e)))	; expect (cond (a b) (c d) (else e))
(if->cond '(if a (if b c d) (if e f g)))	; expect(cond (a (if b c d)) (e f) (else g))
(if->cond '(if a (if b c d) c))    ; expect (cond (a (if b c d)) (else c)
(if->cond 3)                       ; expect 3