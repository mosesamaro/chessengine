(ns chess-engine.core-test
  (:require [clojure.test :refer :all]
            [chess-engine.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))


(def init-tests-state '({
          :board [[:br :bn :bb :bq :bk :bb :bn :br]
                  [:bp :bp :bp :bp :bp :bp :bp :bp]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:wp :wp :wp :wp :wp :wp :wp :wp]
                  [:wr :wn :wb :wq :wk :wb :wn :wr]],
          :white-king-moved false,
          :black-king-moved false,
          :turn :white
          :prev-move nil
          }))


(def basic-tests-state '(
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :ee :bp :ee :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :bp :wp :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :bp :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :white
                 :prev-move :c5
                 :move nil
                 },
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :bp :bp :ee :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :wp :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :bp :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :black
                 :prev-move :d5
                 :move nil
                 }
                ))


          ;; :board [[:br :bn :bb :bq :bk :bb :bn :br]
          ;;         [:bp :bp :bp :bp :be :bp :bp :bp]
          ;;         [:ee :ee :ee :ee :ee :ee :ee :ee]
          ;;         [:ee :ee :ee :ee :ee :ee :ee :ee]
          ;;         [:ee :ee :ee :ee :ee :ee :ee :ee]
          ;;         [:ee :ee :ee :ee :ee :ee :ee :ee]
          ;;         [:wp :wp :wp :wp :wp :wp :wp :wp]
          ;;         [:wr :wn :wb :wq :wk :wb :wn :wr]],


;; -- Test cases for is-move-legal --



;; Move a pawn 1 square

;; Move a pawn 2 squares

;; Issue an error if attempt to move pawn two squares when its already been moved

;; Move a knight

;; Move a knight off board (throws an error)

;; Move a bishop

;; Move a bishop off board (throws an error)

;; Move a bishop where there are blocking pieces (throws an error)

;; Move a rook

;; Move a rook off board (throws an error)

;; Move a rook where there are blocking pieces (throws an error)

;; Move a queen

;; Move a queen where there are blocking pieces

;; Move a king (hasn't been moved)

;; Castle a King legally

;; Castle a King where the king has moved (throws an error)

;; Castle a King where there are blocking pieces

;; Move a King into check (throws an error)


;; -- Test cases for move --

;; Identify checkmate

;; -- Test cases for get-square --

;; (def app-state {
;;           :board [[:r :n :b :q :k :b :n :r]
;;                   [:p :p :p :p :p :p :p :p]
;;                   [:e :e :e :e :e :e :e :e]
;;                   [:e :e :e :e :e :e :e :e]
;;                   [:e :e :e :e :e :e :e :e]
;;                   [:e :e :e :e :e :e :e :e]
;;                   [:p :p :p :p :p :p :p :p]
;;                   [:r :n :b :q :k :b :n :r]],
;;           :white-king-moved false,
;;           :black-king-moved false,
;;           :turn :white
;;           })

(deftest test-get-square
  (testing "get-square"
    (is (= (get-piece-on-pos "a3" (:board (peek basic-tests-state))) :ee))
    (is (= (get-piece-on-pos "d7" (:board (peek basic-tests-state))) :bp))
    (is (= (column-index \a) 1))))


;; -- Test cases for chess-notation-to-move [notation] -- 

(deftest test-chess-notation-to-move 
  (testing "See if we can correctly convert chess notation into a map based move representation"
    (is (= (lex-notation "e4") '({:token \e, :type :col} {:token \4, :type :row})))
    (is (= (get-piece-on-pos "e3" (:board app-state)) 
           {:start-pos "e2" :piece :p :end-pos "e3"}))
    (is (= (chess-notation-to-move "e3" (:board app-state)) 
           {:start-pos "e2" :piece :p :end-pos "e3"}))
    (is (= (chess-notation-to-move "exd3" 
                 [[:br :bn :bb :bq :bk :bb :bn :br]
                  [:bp :bp :bp :bp :ee :bp :bp :bp]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :bp :ee :ee :ee]
                  [:ee :ee :ee :wp :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:wp :wp :wp :ee :wp :wp :wp :wp]
                  [:wr :wn :wb :wq :wk :wb :wn :wr]]) 
           {:start-pos "d4" :piece :wp :end-pos "e5"}))))

;; (deftest pawn-moves-test
;;   (testing "Lets see if we can get en-passant working"
;;     (is (= (is-en-passant? {
;;           :board [[:br :bn :bb :bq :bk :bb :bn :br]
;;                   [:bp :bp :ee :bp :ee :bp :bp :bp]
;;                   [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                   [:ee :ee :bp :wp :ee :ee :ee :ee]
;;                   [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                   [:ee :ee :ee :ee :bp :ee :ee :ee]
;;                   [:wp :wp :wp :ee :wp :wp :wp :wp]
;;                   [:wr :wn :wb :wq :wk :wb :wn :wr]],
;;           :white-king-moved false,
;;           :black-king-moved false,
;;           :turn :white
;;           :prev-move :c5
;;           } \w :d5)))))

;; (is-en-passant? {
;;           :board [[:br :bn :bb :bq :bk :bb :bn :br]
;;                   [:bp :bp :ee :bp :ee :bp :bp :bp]
;;                   [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                   [:ee :ee :bp :wp :ee :ee :ee :ee]
;;                   [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                   [:ee :ee :ee :ee :bp :ee :ee :ee]
;;                   [:wp :wp :wp :ee :wp :wp :wp :wp]
;;                   [:wr :wn :wb :wq :wk :wb :wn :wr]],
;;           :white-king-moved false,
;;           :black-king-moved false,
;;           :turn :white
;;           :prev-move :c5
;;           :prev-board [[:br :bn :bb :bq :bk :bb :bn :br]
;;                        [:bp :bp :bp :bp :ee :bp :bp :bp]
;;                        [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                        [:ee :ee :ee :wp :ee :ee :ee :ee]
;;                        [:ee :ee :ee :ee :ee :ee :ee :ee]
;;                        [:ee :ee :ee :ee :bp :ee :ee :ee]
;;                        [:wp :wp :wp :ee :wp :wp :wp :wp]
;;                        [:wr :wn :wb :wq :wk :wb :wn :wr]],
;;           } \w :d5 :c5)


(def app-state-left-en-passant '(
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :ee :bp :bp :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :bp :wp :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :white
                 :prev-move :c5
                 :move nil
                 },
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :bp :bp :bp :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :wp :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :black
                 :prev-move :d5
                 :move nil
                 }
                ))

(def app-state-right-en-passant '(
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :ee :bp :ee :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :wp :bp :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :white
                 :prev-move :e5
                 :move nil
                 },
                {
                 :board [[:br :bn :bb :bq :bk :bb :bn :br]
                         [:ee :bp :bp :bp :bp :bp :bp :bp]
                         [:bp :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :wp :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:ee :ee :ee :ee :ee :ee :ee :ee]
                         [:wp :wp :wp :ee :wp :wp :wp :wp]
                         [:wr :wn :wb :wq :wk :wb :wn :wr]],
                 :white-king-moved false,
                 :black-king-moved false,
                 :turn :black
                 :prev-move :d5
                 :move nil
                 }
                ))


;;(peek app-state-en-passant)

(deftest en-passant-left-test
  (testing "Lets see if we can get en-passant working"
    (is (= (is-en-passant? app-state-left-en-passant \w :d5 :c6) true))))


(deftest en-passant-right-test
  (testing "Lets see if we can get en-passant working to the right"
    (is (= (is-en-passant? app-state-right-en-passant \w :d5 :e6) true))))


(is-en-passant? app-state-right-en-passant \w :d5 :e6)

(deftest bishop-moves-test
"See if the bishop-move function produces correct possible 
bishop positions, excluding check handling"
(is  (= 
      (bishop-moves '({
                       :board [[:br :bn :bb :bq :bk :bb :bn :br]
                               [:ee :bp :bp :bp :ee :bp :bp :bp]
                               [:bp :ee :ee :ee :ee :ee :ee :ee]
                               [:ee :ee :ee :wp :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :wb :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:wp :wp :wp :ee :wp :wp :wp :wp]
                               [:wr :wn :ee :wq :wk :wb :wn :wr]],
                       :white-king-moved false,
                       :black-king-moved false,
                       :turn :white
                       :prev-move :c5
                       :move nil
                       }), {:pos :f4, :piece :wb})
      '(:e5 :d6 :c7 :g5 :h6 :e3 :d2 :c1 :g3))))

(deftest rook-moves-test
"See if the rook-moves function produces correct possible 
rook positions, excluding check handling"
(is  (= 
     (rook-moves '({
                       :board [[:br :bn :bb :bq :bk :bb :bn :br]
                               [:ee :ee :bp :bp :ee :bp :bp :bp]
                               [:bp :bp :ee :ee :ee :ee :ee :wr]
                               [:ee :ee :ee :wp :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :wb :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:wp :wp :wp :ee :wp :wp :wp :ee]
                               [:wr :wn :ee :wq :wk :wb :wn :ee]],
                       :white-king-moved false,
                       :black-king-moved false,
                       :turn :white
                       :prev-move :c5
                       :move nil
                       }), {:pos :h6, :piece :wr})
     '(:h7 :g6 :f6 :e6 :d6 :c6 :b6 :h5 :h4 :h3 :h2 :h1))))

(deftest queen-moves-test
"See if the queen-moves function produces correct possible 
queen positions, excluding check handling"
(is  (= 
     (queen-moves '({
                       :board [[:br :bn :bb :bq :bk :bb :bn :br]
                               [:ee :ee :bp :bp :ee :bp :bp :bp]
                               [:bp :bp :ee :ee :ee :ee :ee :wq]
                               [:ee :ee :ee :wp :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :wb :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:wp :wp :wp :ee :wp :wp :wp :ee]
                               [:wr :wn :ee :wq :wk :wb :wn :ee]],
                       :white-king-moved false,
                       :black-king-moved false,
                       :turn :white
                       :prev-move :c5
                       :move nil
                       }), {:pos :h6, :piece :wq})
     '(:h7 :g6 :f6 :e6 :d6 :c6 :b6 :h5 :h4 :h3 :h2 :h1 :g7 :g5))))

(deftest knight-moves-test
"See if the knight-moves function produces correct possible 
knight positions, excluding check handling"
(is  (= 
     (knight-moves '({
                       :board [[:br :bn :bb :bq :bk :bb :bn :br]
                               [:ee :ee :bp :bp :ee :bp :bp :bp]
                               [:bp :ee :ee :ee :ee :ee :ee :ee]
                               [:ee :bp :ee :wp :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :wb :ee :ee]
                               [:ee :ee :wn :ee :ee :ee :ee :ee]
                               [:wp :wp :wp :ee :wp :wp :wp :ee]
                               [:wr :ee :ee :wq :wk :wb :wn :ee]],
                       :white-king-moved false,
                       :black-king-moved false,
                       :turn :white
                       :prev-move :c5
                       :move nil
                       }), {:pos :c3, :piece :wn})
     '(:b5 :e4 :a4 :b1))))

(def pawn-test-state
'({
                       :board [[:br :bn :bb :bq :bk :bb :bn :br]
                               [:bp :bp :bp :bp :bp :bp :bp :bp]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:ee :ee :ee :ee :ee :ee :ee :ee]
                               [:wp :wp :wp :wp :wp :wp :wp :wp]
                               [:wr :wn :wb :wq :wk :wb :wn :wr]],
                       :white-king-moved false,
                       :black-king-moved false,
                       :turn :white
                       :prev-move :c5
                       :move nil
                       }))

(deftest pawn-start-test
  (is (=  (process-simple-pawn-start "" :e4 { :start-pos "" 
                                             :piece \p 
                                             :end-pos "" } pawn-test-state)
      {:start-pos :e2
       :piece \p
       :end-pos :e4})))

(pawn-start-test)


