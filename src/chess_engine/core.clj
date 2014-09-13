(ns chess-engine.core)


;; Introduction
;; ================
;; This chess engine application is intended to determine validity of
;; moves and board tracking. It does not include a chess AI component

(def init-app-state '({
          :board [[:br :bn :bb :bq :bk :bb :bn :br]
                  [:bp :bp :bp :bp :ee :bp :bp :bp]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :ee :ee :ee :ee]
                  [:ee :ee :ee :ee :bp :ee :ee :ee]
                  [:wp :wp :wp :wp :wp :wp :wp :wp]
                  [:wr :wn :wb :wq :wk :wb :wn :wr]],
          :white-king-moved false,
          :black-king-moved false,
          :turn :white
          :prev-move nil
          }))

(def app-state '(
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

(defn column-index [letter]
"Takes a letter representing a column, returns an index"
  (get {\a 1, \b 2, \c 3, \d 4, \e 5, \f 6, \g 7, \h 8} letter))

(defn index-column [index]
  "Takes an index, returns a letter representing a column"
 (get {1 \a, 2 \b, 3 \c, 4 \d, 5 \e, 6 \f, 7 \g, 8 \h} index))
            

(defn is-in-check [app-state]
  "Calculates whether the king is in check"
 )

(defn is-move-legal? [app-state move]
"Function determines if a given move on the board
is legal"
)

;; Example legal move
;; (def move 
;;   { :start-pos
;;     :piece
;;     :end-pos })

(def valid-pieces #{\K \Q \N \R \B})

(def valid-cols #{\a \b \c \d \e \f \g \h})

(def valid-rows (set (range 1 9)))

(defn notation-token [tok]
  "This function takes a token from a chess notation string, and classifies
it as a piece, column, row or takes"
  (cond (contains? valid-pieces tok) {:token tok :type :piece}
        (contains? valid-cols tok) {:token tok :type :col}
        (= \x tok) {:token tok :type :takes}
        (integer? (Integer/parseInt (str tok))) {:token tok :type :row}
        :else (prn "Error : Couldn't figure out what notation token is " tok)))

(defn lex-notation [notation]
  "This function converts all the parts of a chess notation string into lexems where lexems contain a token and type"
  (map notation-token notation))

(defn make-keyword [col row]
  (if (nil? row)
    nil
    (if (nil? col)
      nil
      (keyword (str col row)))))

(def make-pos make-keyword)
(def make-piece make-keyword)

(defn first-keyword-char [piece]
      (first (name piece)))

(def get-col first-keyword-char)

(def get-side first-keyword-char)

;;(prn (lex-notation "exd6"))

(defn process-piece-start [tokens res]
  "Processes a chess notation section that starts with a piece"
)

;;(defn get-pawns-by-col [col]

(defn is-valid-col? [col]
  (contains? valid-cols col))

(defn is-valid-row [row]
  (contains? valid-rows row))

(defn second-keyword-char [pos]
  (if (nil? pos)
    nil
    (second (name pos))))

(def get-row second-keyword-char)

(defn inc-row [pos]
        (make-pos 
         (valid-cols (get-col pos))
         (valid-rows (inc (Integer/parseInt (str (get-row pos)))))))


(defn dec-row[pos]
        (make-pos 
         (valid-cols (get-col pos))
         (valid-rows (dec (Integer/parseInt (str (get-row pos)))))))

(inc-row :e4)

(defn inc-col [pos]
  (make-pos 
   (valid-cols (index-column (inc (column-index (get-col pos)))))
   (valid-rows (Integer/parseInt (str (get-row pos))))))

(defn dec-col [pos]
  (make-pos 
   (valid-cols (index-column (dec (column-index (get-col pos)))))
   (valid-rows (Integer/parseInt (str (get-row pos))))))

;;(defn inc-col [pos]

(def get-piece second-keyword-char)

;;(get-row :e2)
;;(conj (pawn-moves app-state {:
;; (get-piece-on-pos :e2 app-state)
;;(pawn-moves app-state {:pos :d2, :piece :wp})

(defn make-keyword [col row]
  (if (nil? row)
    nil
    (if (nil? col)
      nil
      (keyword (str col row)))))

(def make-pos make-keyword)
(def make-piece make-keyword)

(make-pos \e nil)

(defn get-piece-on-pos [pos board]
"Takes a square name (ex: a3) and returns the piece on it"
 (let [col (column-index (get-col pos))
      row (Integer/parseInt (str (get-row pos)))]
;;       board (:board (peek app-state))]
   (get (get board (- 8 row)) (dec col) :ee)))



(get-piece-on-pos :e3 (:board (peek app-state)))

(defn is-free? [app-state pos]
      (= (get-piece-on-pos pos (:board (peek app-state))) :ee))

(is-free? app-state :e3)

(defn is-enemy? [app-state my-side pos]
      (if (nil? pos) false
          (= (get-side (get-piece-on-pos pos (:board (peek app-state)))) 
             (if (= my-side \w) \b \w))))

(is-enemy? app-state \w :b8)

(defn is-free-or-enemy? [app-state my-side pos]
      (if (nil? pos) false
          (or (is-free? app-state pos) 
              (is-enemy? app-state my-side pos))))

(defn step [step-forward step-backward]
  "Takes a position, application state and optional side. Returns the
  pos that resides on the square one up from the piece, from the
  perspective of the given side. If side is not provided, defaults to
  the side of the piece if one exists, or black"
  (fn [side app-state pos]
  (if (nil? pos)
    nil
    (let [piece (get-piece-on-pos pos (:board (peek app-state)))
          side (if (= side nil) 
                 (get-side piece)
                 side)]
      (if (= side \w)
        (step-forward pos)
        (step-backward pos))))))

(def up (step inc-row dec-row))
(def down (step dec-row inc-row))
(def left (step dec-col dec-col))
(def right (step inc-col inc-col))

;; Function which moves in a given direction, by repeatedly 
;; executing some function, as long as the squares around it 
;; are empty and on the board. Returns all squares which are
;; empty or contain a piece of the opposite faction
;; (defn path
;;   ([pos app-state step]
;;   (path pos app-state step []))
;;   ([pos app-state step res]
;;   (let [steps (take 7 (rest (iterate step pos)))]
;;     steps)))

(defn path [pos step]
 (take 7 (rest (iterate step pos))))

(defn passing-pawn-row? [side]
"passing-paw-rows are rows you need to be on to perform en-passant according to your side"
(if (= side \w) \5 \4))

(defn get-position-functions [my-side app-state]
  (map #(partial % my-side app-state) [up down left right]))

;; Jesus this is harder to do than I originally thought...
;; curr-pos is the position of my current piece. 
;; pos is the position that I'm checking, should be up
;; and left|right one square.
;; Pretty sure I've thought throught this before, but I want
;; to check to ensure that 
;; 
;; If a previous move was 

(defn is-en-passant? [app-state my-side curr-pos pos]
  (let [[up down left right] (get-position-functions my-side app-state)

        ;; Whether your curr-pos (position for your current piece is on the 
        ;; right row for en-passant
        on-right-row? (= (get-row curr-pos) (passing-pawn-row? my-side)) 

        ;; The previous move (should be a pawn move onto on of the passing pawn
         ;; rows)
        prev-move (:prev-move (peek app-state))

        ;; Row that pawns start from, according to side
        enemy-starting-row (if (= my-side \w) \7 \2)
        enemy-side (if (= my-side \w) \b \w)

       ;; Check that position is diagnal from current position
        position-is-diagnal (= pos (-> curr-pos left up))

        ;; Check that the previous move put the enemy piece 
        ;; next to our piece
        enemy-pawn-is-adjacent (= (make-pos 
                                   (get-col (-> curr-pos left))
                                   (passing-pawn-row? my-side)) 
                                  prev-move)

        prev-move-match 
        (or 
         (and 
          position-is-diagnal
          enemy-pawn-is-adjacent
          ;; Check that the there was a pawn on the home row
          ;; in the board state for the last move that no longer
          ;; exists on the home row. If that pawn disappeared, 
          ;; and a pawn showed up adjacent to our piece, we know
          ;; en passant is possible. The middle square must be
          ;; empty since the previous move put a pawn on the 
          ;; square adjacent to our piece. The pawn could not 
          ;; have come from a diagnal move, because then the 
          ;; pawn on the home row would not have disappeared. 
          ;; This is enough checking
          ;; Check that prev board had a black pawn on a starting square
          ;; left of our current pawn
          (= (get-piece-on-pos (make-pos 
                                (get-col (-> curr-pos left))
                                enemy-starting-row)
                               (:board (peek (rest app-state))))
             (make-piece enemy-side \p))
          ;; Check that the starting square for our enemies pawn
          ;; left of our current pawn is now empty
          (= (get-piece-on-pos (make-pos 
                                (get-col
                                 (-> curr-pos left)) 
                                enemy-starting-row) 
                               (:board (peek app-state))) 
             :ee))
         (and
          (= pos (-> curr-pos right up))
     (= (make-pos
              (get-col (-> curr-pos right))
              (passing-pawn-row? my-side))
             prev-move)))]
    (do (prn on-right-row? prev-move prev-move-match position-is-diagnal enemy-pawn-is-adjacent prev-move)
        prev-move-match))) ;; End of en-passant


(defn is-free-or-enemy2 [app-state side positions]
      (:pos
      (let [is-free? (partial is-free? app-state)
            is-enemy? (partial is-enemy? app-state side)]
        (reduce (fn [acc curr]
                  (cond 
                   (nil? curr) { :blocked true,
                                     :pos (:pos acc) }
                   (:blocked acc) acc
                   (is-free? curr) { :blocked (:blocked acc),
                                    :pos (conj (:pos acc) curr) }
                   (is-enemy? curr) { :blocked true,
                                     :pos (conj (:pos acc) curr) }
                   :else            { :blocked true,
                                     :pos (:pos acc) }))
                { :blocked  false, :pos  [] } positions))))

(defn is-enemy-or-en-passant? [app-state my-side pos]
  (or (is-enemy? app-state my-side pos)
      (is-en-passant? app-state my-side pos)))

;; (defn is-free-or-enemy2 [positions]
;;   (do (println "positions : " positions " END")
;;       (reduce (fn [acc curr]
;;                 (cond 
;;                  ((:blocked acc) acc)
;;                  ((is-free? curr) { :blocked (:blocked acc)
;;                                     :pos (conj (:pos acc) curr) })
;;                  ((is-enemy? curr) { :blocked true
;;                                      :pos (conj (:pos acc) curr) })))
;;                  { :blocked : false, :pos : []} positions)))


(defn bishop-moves [app-state unit]
  (let [pos (:pos unit)
        piece (:piece unit)
        side (get-side piece)
        [up down left right] (get-position-functions side app-state)
;;        is-free-or-enemy? (partial is-free-or-enemy? app-state side)
        ]
    (concat 
     (is-free-or-enemy2 app-state side (path pos (comp up left)))
     (is-free-or-enemy2 app-state side (path pos (comp up right)))
     (is-free-or-enemy2 app-state side (path pos (comp down left)))
     (is-free-or-enemy2 app-state side (path pos (comp down right))))))

(defn rook-moves [app-state unit]
  (let [pos (:pos unit)
        piece (:piece unit)
        side (get-side piece)
        [up down left right] (get-position-functions side app-state)
;;        is-free-or-enemy? (partial is-free-or-enemy? app-state side)
        ]
    (concat 
     (is-free-or-enemy2 app-state side (path pos up))
     (is-free-or-enemy2 app-state side (path pos right))
     (is-free-or-enemy2 app-state side (path pos left))
     (is-free-or-enemy2 app-state side (path pos down)))))

(defn queen-moves [app-state unit]
  (concat (rook-moves app-state unit)
          (bishop-moves app-state unit)))

(defn knight-moves [app-state unit]
"Not a Bob Seger song, but the allowed moves of a knight
excluding check. This function takes an application state
and a unit"
  (let [pos (:pos unit)
        piece (:piece unit)
        side (get-side piece)
        [up down left right] (get-position-functions side app-state)
        is-free-or-enemy2 (partial is-free-or-enemy2 app-state side)
        first-knight-path-elm (fn [dir1 dir2 dir3]
                                (list (first (path pos (comp dir1 dir2 dir3)))))
        ]
    (concat 
     (is-free-or-enemy2 (first-knight-path-elm up up left))
     (is-free-or-enemy2 (first-knight-path-elm up up right))
     (is-free-or-enemy2 (first-knight-path-elm up right right))
     (is-free-or-enemy2 (first-knight-path-elm up left left))
     (is-free-or-enemy2 (first-knight-path-elm down down right))
     (is-free-or-enemy2 (first-knight-path-elm down down left))
     (is-free-or-enemy2 (first-knight-path-elm down right right))
     (is-free-or-enemy2 (first-knight-path-elm down left left)))))

;;(path :f4

;; Less elegant but hopefully workable filter for movement.
;; Takes a position, expects to be called multiple times
;; returns true if the square is empty, or if the square is not
;; empty but is the first enemy square. Should return false on
;; all squares after an enemy square. Should also return false
;; after running into a friendly unit forever

;; (def is-free-or-enemy? 
;;   ((fn []
;;      (let [blocked? false]
;;        (fn [app-state my-side pos]
;;          (if blocked? false 
             
;;      ))))))


  


(def wtf (bishop-moves '({
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
               }), {:pos :f4, :piece :wb})) 

wtf

(def bishop-state '({
               :board [[:br :bn :bb :bq :bk :bb :bn :br]
                       [:ee :bp :ee :bp :ee :bp :bp :bp]
                       [:bp :ee :ee :ee :ee :ee :ee :ee]
                       [:ee :ee :bp :wp :ee :ee :ee :ee]
                       [:ee :ee :ee :ee :ee :wb :ee :ee]
                       [:ee :ee :ee :ee :ee :ee :ee :ee]
                       [:wp :wp :wp :ee :wp :wp :wp :wp]
                       [:wr :wn :ee :wq :wk :wb :wn :wr]],
               :white-king-moved false,
               :black-king-moved false,
               :turn :white
               :prev-move :c5
               :move nil
               }))

(is-free-or-enemy? bishop-state \w :b8)

(is-enemy? bishop-state \w :b8)

      (= (get-side (get-piece-on-pos :b8 bishop-state)) 
             (if (= \w \w) \b \w))

(get-side (get-piece-on-pos :b8 bishop-state))


        
;; Pawns can move forward 1 or 2 squares if they are on their starting square
;; They can move 1 square forward if nothing is blocking them
;; They can attack diagnolly-forward one square
(defn pawn-moves [app-state unit]
"This function takes a board and a unit (where a unit is a pawn
 or piece and its position) and returns a sequence containing all
 squares the unit can move to"
(let [pos (:pos unit)
      piece (:piece unit)
      side (get-side piece)
      starting-square (= (get-row pos) (if (= side \w) \2 \7))
      [up down left right] (get-position-functions side app-state)
      is-free? (partial is-free? app-state)
      is-enemy? (partial is-enemy? app-state side)]
  (concat
       (take 2 (take-while is-free? (path pos app-state up)))
       (take 1 (take-while is-enemy? (path pos app-state (comp up left))))
       (take 1 (take-while is-enemy? (path pos app-state (comp up right)))))))

;; (let [up (partial up \w app-state)
;;       down (partial down \w app-state)        
;;       left (partial left \w app-state)
;;       right (partial right \w app-state)]
;;     (path :h1 app-state (comp up left)))


;;(get-piece-on-pos :e3 app-state)

;;(up :e3 app-state \b)
;;(right :e3 app-state \w)

;;(pawn-moves app-state {:pos "e2" :piece ":wp"})


;; We recieved a simple pawn start, like :e4
;; A pawn must have existed one or two columns behind
;; the destination
;; Here we get the desired location for a pawn. We know its simple because
;; of the structure of the command (ex :e4). We're not taking anything. 
;; We get the desired locatoin and backtrack to see if we can get 

(defn process-simple-pawn-start [tokens pos res app-state]
   (let [
         [up down left right] (get-position-functions (:turn (pop app-state)) app-state)
         one-step-back (-> pos down)
         two-steps-back (-> pos down down)
         ]
     (cond 
      (= (get-piece-on-pos one-step-back app-state) \p) (assoc res :start-pos one-step-back)
      (= (get-piece-on-pos two-steps-back app-state) \p) (assoc res :start-pos two-steps-back)
      :else nil)))

;;(let [   [up down left right] (get-position-functions (:turn (pop app-state)) app-state)
;;         ]
;; (-> :e4 down))

     ;; Was there a pawn two squares back, that was on a starting
   ;; row?
;;  (assoc res :end-pos pos)

(defn process-pawn-start [tokens pos res app-state]
  "Processes a chess notation section that relates to the movement of a pawn"
  (if (empty? tokens)
        (process-simple-pawn-start tokens pos res app-state)
      ))


;; I initially thought that I could do this without having a board, I now
;; realize that this is not possible. Legal moves can only be determined by 
;; a move and a board. If the goal of this function is to give validated
;; board moves to a move function, then it must perform perfect validation
;; and perfect validation requires a board (really it requires an app-state
;; which contains multiple boards... this is needed for en-passant checking).
;;
;; Rough notation describing chess notation parsing
;; (notation piece-spec | pawn-start)
;; (take-pos x pos)
;; (pawn-start pos | col take-pos)
;; (pos col row)
;; (piece-spec piece-start | piece-start piece-end)
;; (piece-start piece pos | piece col)
;; (piece-end pos | take-pos)
;;
;; Rules
;; If no piece is specified, its a pawn
;; Syntax Variants:
;; pos (ex: e3) - pawn move, should be a pawn on the column specified by pos
;; piece pos (ex: nc3) - Indicates that a knight should be moved to c3
;; piece col pos - Indicates that a piece on the column should be moved to c3
;; piece pos pos - Indicates that a piece on the pos should be moved to the new pos
;; col x pos - pawn capture, pawn on column takes position
;; piece col x pos - piece capture
;; piece pos x pos - piece capture
;; O-O indicates castling
;; O-O-O indicates queenside castling
(defn parse-notation [notation app-state]
  "Parse a notation string into a hash representing a move for easier future processing"
  (cond (= "O-O" notation) :castle
        (= "O-O-O" notation) :castle-queenside
        :else 
        (let [tokens (lex-notation notation)
              first-tok (first tokens)
              ]
          (cond (= (:type first-tok) :piece)
                (process-piece-start (rest tokens) {:start-pos ""
                                                    :piece first-tok
                                                    :end-pos ""} app-state)
                (= (:type first-tok) :col)
                (process-pawn-start (rest (rest tokens))
                                    (apply make-pos (map #(:token %) (take 2 tokens)))
                                    {:start-pos "", :piece \p, :end-pos ""}
                                    app-state)
                :else nil))))

(parse-notation "e5" app-state)



;; Respon
(defn chess-notation-to-move [notation board]
  "Takes a string containing chess notation, and converts it into a move"
)

;; Move, top level interface to the chess engine
;; takes a move in chess notation format, and an app state. 
;; if no app-state is provided, it is assumed that we're
;; dealing with a new game.
(defn move 
  ([move app-state]
     (prn move app-state))
  ([move]
     (prn move)))




;;(get-square "d7" (:board app-state))
