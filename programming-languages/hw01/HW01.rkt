#lang scheme
;---------------------------------------------------------------------
;
;    PROGRAM NAME: HW01 - area, between, shorter, righttri
;
;    DESCRIPTION: This file contains the code for HW01
;
;    AUTHOR: 	Victor A. Lora
;
;    HISTORY: 	01/22/2016 - started
;	01/24/2016 - revised & finished
;
;    NOTES:	N/A
;
;---------------------------------Code---------------------------------

;circlearea function
(define circlearea
    (lambda (x) (* pi (* x x)))
)

;test for circlearea
(circlearea 3)
(circlearea 2)


;between function
(define between?
    (lambda (a x y)
        (cond
            ((and (> a x) (< a y)) #t)
            (#t #f)
        )
    )
)

;test for between?
(between? 4 2 7)
(between? 4 1 4)
(between? 1 0 5)


;shorter function
(define shorter
    (lambda (x y)
        (cond
            ((and (null? x) (null? y)) "both lists were empty")
            ((null? x) "first list was empty")
            ((null? y) "second list was empty")
            ((> (length x) (length y)) y)
            ((< (length x) (length y)) x)
            ((= (length x) (length y)) x)
            (#t "Uhhh... something went wrong. Try again.")
        )
    )
)

;test for shorter
(shorter '(a b) '(c d e))
(shorter '(a b) '(c d))
(shorter '(a b) '(c))
(shorter '() '(c))
(shorter '(a b) '())
(shorter '() '())


;function righttri?
(define righttri?
    (lambda (x)
        (cond
            ((null? x) "empty list")
            ((and (= (first x) 0) (and (= (first x) (second x)) (= (first x) (third x)))) #t)
            ((and (> (first x) (second x)) (> (first x) (third x)))
                (cond
                    ((= (* (first x) (first x)) (+ (* (second x) (second x)) (* (third x) (third x)))) #t)
                    (#t #f)
                )
            )
            ((and (> (second x) (first x)) (> (second x) (third x)))
                (cond
                    ((= (* (second x) (second x)) (+ (* (first x) (first x)) (* (third x) (third x)))) #t)
                    (#t #f)
                )
            )
            ((and (> (third x) (second x)) (> (third x) (first x)))
                (cond
                    ((= (* (third x) (third x)) (+ (* (second x) (second x)) (* (first x) (first x)))) #t)
                    (#t #f)
                )
            )
            ((and (> (first x) (second x)) (= (first x) (third x))) #f)
            ((and (> (first x) (third x)) (= (first x) (second x))) #f)
            ((and (> (second x) (first x)) (= (second x) (third x))) #f)
            ((and (> (second x) (third x)) (= (second x) (first x))) #f)
            ((and (> (third x) (second x)) (= (third x) (first x))) #f)
            ((and (> (third x) (first x)) (= (third x) (second x))) #f)
            ((and (= (third x) (second x)) (= (third x) (first x))) #f)
        )
    )
)

;test righttri?
(righttri? '(2 4 3))
(righttri? '(5 3 4))
(righttri? '(1 1 1))
(righttri? '(2 1 2))
(righttri? '(1 2 2))
(righttri? '(2 2 1))
(righttri? '(0 0 0))