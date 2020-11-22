#lang racket

;---------------------------------------------------------------------
;
;    FILE NAME:    HW05.rkt
;
;    DESCRIPTION:  bintree, bintree_path
;
;    AUTHOR:       Victor A. Lora
;
;    HISTORY:      03/04/2016 - started & finished
;
;    NOTES:	For bintree_path I tweaked the outputs a little so
;                  that text would be provided to the user, giving them
;                  more detailed information. Instead of returning '()
;                  for both not found and n being the root, I provided
;                  either "not in tree" or "root." Additionally, I
;                  checked for invalid input and informed the user
;                  with some helpful hints.
;
;---------------------------------------------------------------------

;============================ (path n bst) ===========================

;; NAME: path_n_bst
;;
;; DESC: A procedure that returns a list of directions needed to
;;       reach the search parameter in a binary tree
;;
(define path_n_bst
  (λ (n bst)
    (letrec ([helper
              (λ (n bst lst)
                (cond
;                  [(null? bst) '()]
                  [(null? bst) "not in tree"]
                  [(eq? (car bst) n) lst]
                  [(< n (car bst)) (helper n (cadr bst) (append lst '(left)))]
                  [(> n (car bst)) (helper n (caddr bst) (append lst '(right)))]
                  ))])
;      (helper n bst '())
      (cond
        [(not (list? bst)) "Error Invalid tree."]
        [(null? bst) "Error The given tree was empty"]
        [(not (integer? n)) "Error Invalid search value."]
        [(eq? n (car bst)) "root"]
        [#t (helper n bst '())]
        ))))

;------------------------- bintree_path tests ------------------------
; test-code - should return '(right left left)
(path_n_bst 17 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return '(left right)
(path_n_bst 12 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "not in tree"; alternatively, '() if we replaced
;   the text with '()
(path_n_bst 30 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "root" if n is the root of the tree;
;   alternatively, '() if we replace the text "root" with '(), or
;   get rid of the cond statement and leave only (helper n bst '())
(path_n_bst 14 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "ERROR: The given tree was empty"
(path_n_bst 14 '())

; test-code - should return "Error: Invalid tree."
(path_n_bst 14 '14)

; test-code - should return "Error: Invalid search value."
(path_n_bst '(14 15) '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

;============================== bintree =============================

;; NAME: make-tree
;;
;; DESC: A procedure that creates a binary tree
;;
(define make-tree
  (λ (val lchild rchild)
    (cond
      [(and (list? lchild)
            (list? rchild)
            (not (or (null? lchild) (null? rchild)))
            (> (car lchild) (car rchild)))
       (list val rchild lchild)]
      [(and (list? lchild)
            (integer? rchild)
            (not (null? lchild))
            (> (car lchild) rchild))
       (list val rchild lchild)]
      [(and (integer? lchild)
            (list? rchild)
            (not (null? rchild))
            (> lchild (car rchild)))
       (list val rchild lchild)]
      [(and (integer? lchild)
            (integer? rchild)
            (> lchild rchild))
       (list val rchild lchild)]
      [#t (list val lchild rchild)]
    )))

;; NAME: tree?
;;
;; DESC: A procedure that returns true if
;;       given a binary tree; false otherwise.
;;
(define tree?
  (λ (tree)
    (if (and (list? tree)
             (eq? (length tree) 3)
             (not (list? (car tree))))
        #t
        #f)))

;; NAME: get-value
;;
;; DESC: A procedure that returns the root of a binary tree.
;;
(define get-value
  (λ (tree)
    (cond
      [(not (list? tree)) '()]
      [(null? tree) '()]
      [#t (car tree)]
    )))

;; NAME: get-left
;;
;; DESC: A procedure that returns the lchild of a binary tree
;;
(define get-left
  (λ (tree)
    (cond
      [(not (list? tree)) '()]
      [(null? tree) '()]
      [(null? (cadr tree)) '()]
      [#t (cadr tree)]
      )))

;; NAME: get-right
;;
;; DESC: A procedure that returns the rchild of a binary tree
;;
(define get-right
  (λ (tree)
    (cond
      [(not (list? tree)) '()]
      [(null? tree) '()]
      [(null? (caddr tree)) '()]
      [#t (caddr tree)]
      )))
      
;--------------------------- bintree tests --------------------------
;test code - should return '(9 (5 4 6) 10)
(make-tree 9 (make-tree 5 6 4) 10)

;test code - should return '(9 (5 4 6) 10)
(make-tree 9 10 (make-tree 5 6 4))

;test code - should return '(9 (5 (4 1 2) 6) 10)
(make-tree 9 10 (make-tree 5 6 (make-tree 4 2 1)))

; test code - should return 2
; (value of the root of tree with root 2 and children 1 and 3)
(get-value (make-tree 2 (make-tree 1 '() '())
                                   (make-tree 3 '() '()) ) )
; test code - should return 3
; (value of right child of tree with root 2 and children 1 and 3)
(get-value (get-right (make-tree 2 (make-tree 1 '() '())
                                   (make-tree 3 '() '()) ) ))
; test code - should return 1
; (value of left child of tree with root 2 and children 1 and 3)
(get-value (get-left (make-tree 2 (make-tree 1 '() '())
                                   (make-tree 3 '() '()) ) ))
; test code - should return 3
; (value of right child of tree with root 2 and children 1 and 3)
(get-value (get-right (make-tree 2 (make-tree 3 '() '())
                                   (make-tree 1 '() '()) ) ))



;============================ bintree_path ===========================
;; NAME: path
;;
;; DESC: A procedure that returns a list of directions needed to
;;       reach the search parameter in a binary tree using the
;;       datatype bintree, created above.
;;
(define path
  (λ (n bst)
    (letrec ([helper
              (λ (n bst lst)
                (cond
;                  [(null? bst) '()]
                  [(null? bst) "not in tree"]
                  [(eq? (car bst) n) lst]
                  [(< n (car bst)) (helper n (get-left bst) (append lst '(left)))]
                  [(> n (car bst)) (helper n (get-right bst) (append lst '(right)))]
                  ))])
;      (helper n bst '())
      (cond
        [(null? bst) "Error The given tree was empty"]
        [(not (tree? bst)) "Error Invalid tree."]
        [(not (integer? n)) "Error Invalid search value."]
        [(eq? n (get-value bst)) "root"]
        [#t (helper n bst '())]
        ))))

;------------------------- bintree_path tests ------------------------
; test-code - should return '(right left left)
(path 17 (make-tree 14 (make-tree 7 '() (make-tree 12 '() '()))
                       (make-tree 26 (make-tree 20 (make-tree 17 '() '()) '())
                                     (make-tree 31 '() '()))))

; test-code - should return '(left right)
(path 12 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "not in tree"; alternatively, '() if we replaced
;   the text with '()
(path 30 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "root" if n is the root of the tree;
;   alternatively, '() if we replace the text "root" with '(), or
;   get rid of the cond statement and leave only (helper n bst '())
(path 14 '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))

; test-code - should return "ERROR: The given tree was empty"
(path 14 '())

; test-code - should return "Error: Invalid tree."
(path 14 '14)

; test-code - should return "Error: Invalid search value."
(path '(14 15) '( 14 (7 () (12 () ())) (26 (20 (17 () ()) ()) (31 () ()))))