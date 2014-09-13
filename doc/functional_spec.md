Chess Engine
============

The ChessEngine is a clojure based chess engine. It includes
validity checking of moves, checkmate analysis and understands
standard chess notation.

How it works
============

The ChessEngine stores the following as application state:

  * A stack, where each element in the stack represents a move

  Within each move the following is stored:

  * A board, which is composed of keywords each representing pieces
    with the keyword :ee representing an empty space
  * A flag representing whether the white king has moved
  * A flag representing whether the black-king has moved
  * A flag stating whether its white or black's move

Overview
--------

1. Chess notation is given
2. The Chess Notation is parsed using the notation-token and lex-notation
functons into move sequences that look like: 

    ({:token \e, :type :col} {:token \x, :type :takes} {:token \d, :type :col} {:token \6, :type :row})

3. The type of the piece being moved is identified via the starting piece, if no starting piece is given, it must be a pawn.

4. We find the pawn or piece by running simple functions that return the squares that a given piece can attack.

5. Once the piece has been found, we create our final move object, which tells us
what the start position was for the moving piece, what the piece type is, and what the end position for the piece is. 

6. We then begin the move validation process. 

The move validation process consists of many checks

* Has our game ended? If we already have a checkmate, then we cannot process further moves

* Check determination, are we in check, and if we are, does our move result in us not being in check

* Does the move result in checkmate?

Validation Responsibilities:


| Move Type   | Notation-Parsing | Move Validation |
|-------------+------------------+-----------------|
| Pawn Move   |                  |                 |
| Knight Move | Everything       |                 |





Reading Chess Notation
----------------------

;; How it works?

Input Chess Notation

Chess notation is fed through the parse validation function

This function calls validation functions to determine whether a given move is legal given the current application state. If the move is indeed legal, a hash representing a move is returned. 

The hash contains

{
 :end-pos
 :start-pos
 :piece   
}

This can be passed to a move function, which just performs the move by generating a new move structure and pushing it onto the stack. 

Top level API:

(move [move app-state]
      [move])

Returns a new app-state

(moves app-state move1 move2 move3)

Returns an app state that results after the sequence of provided moves has been made.

(test-moves move1 move2 move3)

Returns a board, used for testing purposes

(move (move (move 1 :e4) :e5) :nc3)

Examples progression of application state through a series of moves:


Initial state:
--------------
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
          :prev-move nil
          }))

Application state after :e4:
----------------------------

'(
  {
   :board [[:br :bn :bb :bq :bk :bb :bn :br]
           [:bp :bp :bp :bp :bp :bp :bp :bp]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:ee :ee :ee :ee :wp :ee :ee :ee]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:wp :wp :wp :wp :ee :wp :wp :wp]
           [:wr :wn :wb :wq :wk :wb :wn :wr]],
   :white-king-moved false,
   :black-king-moved false,
   :turn :black
   :prev-move :e4
   },
  {
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

Application state after :e4 :e5
----------------------------
'(
  {
   :board [[:br :bn :bb :bq :bk :bb :bn :br]
           [:bp :bp :bp :bp :ee :bp :bp :bp]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:ee :ee :ee :ee :bp :ee :ee :ee]
           [:ee :ee :ee :ee :wp :ee :ee :ee]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:wp :wp :wp :wp :ee :wp :wp :wp]
           [:wr :wn :wb :wq :wk :wb :wn :wr]],
   :white-king-moved false,
   :black-king-moved false,
   :turn :white
   :prev-move :e5
   },

  {
   :board [[:br :bn :bb :bq :bk :bb :bn :br]
           [:bp :bp :bp :bp :bp :bp :bp :bp]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:ee :ee :ee :ee :wp :ee :ee :ee]
           [:ee :ee :ee :ee :ee :ee :ee :ee]
           [:wp :wp :wp :wp :ee :wp :wp :wp]
           [:wr :wn :wb :wq :wk :wb :wn :wr]],
   :white-king-moved false,
   :black-king-moved false,
   :turn :black
   :prev-move :e4
   },
  {
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

