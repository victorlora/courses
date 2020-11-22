#lang scheme
;---------------------------------------------------------------------
;
;    FILE NAME:    HW02.rkt
;
;    DESCRIPTION:  invert, vector-index, count-occurrences, compose23
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      1/30/2016 - started & finished
;
;    NOTES:	       N/A
;
;---------------------------------Code---------------------------------


;; --------------------- Procedure ---------------------
;; NAME: invert
;;
;; DESC: A procedure that takes a list of 2-lists;
;;         returns a list with each 2-list reversed.
;;
(define invert
  (lambda (list)
    (cond
      [(null? list) '()]
      [(null? (car list)) (invert (cdr list))]
      [#t (cons (reverse (car list)) (invert (cdr list)))]
      )))

;; -------------------- invert TEST --------------------
(invert '((a b) (c d) (e f))) ; expect ((b a) (d c) (f e))
(invert '())                  ; expect ()
(invert '((a b) () (e f)))    ; expect ((b a) (f e))




;; --------------------- Procedure ---------------------
;; NAME: vector-index
;;
;; DESC: A procedure that returns the zero-based index of the
;;         first occurence of a parameter in a vector, or -1 if
;;         there is no occurrence.
;;
(define vector-index
  (lambda (param a-vector)
    (define count-procedure
      (lambda (count param a-vector)
        (cond
          [(not (vector? a-vector)) "Error: Failed to provide a vector"]
          [(null? vector) -1]
          [(eq? count (vector-length a-vector)) -1]
          [(eq? param (vector-ref a-vector count)) count]
          [#t (count-procedure (+ count 1) param a-vector)]
          )))
    (count-procedure 0 param a-vector)))

;; ----------------- vector-index TEST -----------------
(vector-index 'a #(a b c d))    ; expect 0
(vector-index 'c #(a b c d))    ; expect 2
(vector-index 'e #(a b c d))    ; expect -1
(vector-index 'd '(a b c d))    ; expect error
(vector-index 'f #())           ; expect -1



;; --------------------- Procedure ---------------------
;; NAME: count-occurrences
;;
;; DESC: A procedure that returns the number of
;;         occurrences of a parameter in a list.
;;                    
(define count-occurrences
  (lambda (param a-list)
    (cond
      [(not (list? a-list)) 0]
      [(null? a-list) 0]
      [(list? (car a-list)) (+ (count-occurrences param (car a-list))
                               (count-occurrences param (cdr a-list)))]
      [(eq? param (car a-list)) (+ (count-occurrences param (cdr a-list)) 1)]
      [#t (count-occurrences param (cdr a-list))]
      )))

;; -------------- count-occurrences TEST ---------------
(count-occurrences 'x '((f x) y (((x z) x))))    ; expect 3
(count-occurrences 'w '((f x) y (((x z) x))))    ; expect 0
(count-occurrences 'z '((f z) z (((x z) z))))    ; expect 4
(count-occurrences 'z #(a b c))                  ; expect 0
(count-occurrences 'a '())                       ; expect 0



;; --------------------- Procedure ---------------------
;; NAME compose23
;;
;; DESC: A procedure that takes 1, 2, or 3
;;         procedures and composes them.
;;
(define compose23
  (lambda func
    (cond
      [(eq? 1 (length func)) (lambda (x) ((car func) x))]
      [(eq? 2 (length func)) (lambda (x) ((car func) ((cadr func) x)))]
      [(eq? 3 (length func))
         (lambda (x) ((car func) ((cadr func) ((caddr func) x))))]
      [#t (lambda (x) (display "Error: Invalid parameters."))]
      )))
      
;; ------------------ Compose23 TEST -------------------
((compose23 car) '(1 2 3 4 5))              ; expect 1
((compose23 car cdr) '(1 2 3 4 5))          ; expect 2
((compose23 car cdr cdr) '(1 2 3 4 5))      ; expect 3
((compose23 car cdr cdr cdr) '(1 2 3 4 5))  ; expect err.