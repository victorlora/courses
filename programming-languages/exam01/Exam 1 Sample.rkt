#lang racket

;Question 2a
(define check-tri
  (λ (a b)
    (λ (c)
      (if (eq? (+ (* a a) (* b b)) (* c c))
          #t
          #f)
      )))
((check-tri 3 4) 5)

;Question 1b
(define replace
  (λ (v1 v2)
    (λ (lst)
      (letrec (
               [helper (λ (lst new-lst)
                         (cond
                           [(null? lst) (reverse new-lst)]
                           [(list? (car lst))
                            (helper (cdr lst) (append (list(helper (car lst) '())) new-lst))]
                           [(eq? v1 (car lst)) (helper (cdr lst) (cons v2 new-lst))]
                           [#t (helper (cdr lst) (cons (car lst) new-lst))]
                           ))])
        (cond
          [(null? lst) '()]
          [(not (list? lst)) lst]
          [#t (helper lst '())]
          )))))

((replace 'e 'b) '(a e c (d e) c d))

;Question 3b
(define dup
  (λ (n item)
    (cond
      [(eq? n 0) '()]
      [#t (append (list item) (dup (- n 1) item))]
      )))

(dup 3 '(a b))
(dup 4 'a)

