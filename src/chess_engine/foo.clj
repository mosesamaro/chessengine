


(+ 3 4)

60 = 58 * 17

1w = 17h/1h
58w = 17 * 58 /1h 10 cubic feet

(/ 986 3.4)

290/60

(/ 290 60)

(29.6 * x) = 60

60/29.6

(/ 60 29.6)

(* 17 58)

58w = 986/h

5 to Widgets Boutique 1651
32 to Widgets Emporium 1166
28 Gizmos warehouse 729
14 Gizmos and Gadgets 939 26% of the time

Worst case for widget (5 * 28) + 1651

Assuming that gizmos are there:

14 Miles 3374
14 * 2 * 28 = 
(* 14 2 28) 784
(+ 939  1651) 2590
(+ 2590 784) 3374

28 Miles 3948
(defn gas_cost [miles] 
  (* 2 28 miles))

(defn parts_cost [widget_cost gizmo_cost]
  (+ widget_cost gizmo_cost))

(gas_cost 28) 1568
(parts_cost 1651 729) 2380
(+ 1568 2380) 3948

32 miles 3687
(+ (gas_cost 32)
   (parts_cost 1166 729))

26 3374
74 3687

(- 3687 3374) 313
(* 0.74 313) 231.62
(+ 3374 231.62)

         zup zdown nw ne sw se
die1     6    ?    ?  x  ?  3
die2     2    ?    ?  ?  x  y
die3     3    ?    y  ?  5  ?

Structure 
of the
die with 6  6
zup 

Either 6      ?    ? maybe5  maybe5   3
       6     [2, 4, 1],  [2, 4, 1],  [5 | [2, 4, 1]], [5 |[2, 4, 1]] 3



3 is adjacent to 6 and 5
6 and 5 are adjacent to one another

1. die1  6    ?    ?  5  2  3
   die2  2    5    ?  ?  5  y
   die3  3    ?    y  ?  5  6

6     [4, 1],  [4, 1],  5, 2, 3


Contradiction, 

2.
         1    1    2  3  3  2
         zup zdown nw ne sw se
die1     6    ?    ?  5  1  3
die2     2    ?    ?  1  5  y
die3     3    ?    y  1  5  ?

         zup zdown nw ne sw se

       6     [2, 4],  [2, 4],  5, 1, 3

Contradiction

3. 
         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    ?    ?  x  5  3
die2     2    ?    ?  ?  x  y
die3     3    ?    y  ?  5  6

6     [2, 4, 1],  [2, 4, 1],   [2, 4, 1],  5, 3

6     2   4   1 5 3

die1     6    2    4  1  5  3
die2     2    6    ?  5  1  2
die3     3    4    2  ?  5  6

contradiction

die1     6    ?    ?  x  5  3
die2     2    ?    ?  ?  x  y
die3     3    ?    y  ?  5  6

6     4   2   1 5 3

die1     6    4    2  1  5  3
die2     2    3    3  5  1  4
die3     3    2    4  ?  5  6

Contradiction

123
132
213
312
231
321

[2 4 1]
[4 2 1]
[2 1 4]
[4 1 2]
[1 2 4]
[1 4 2]

[2 1 4]

6 2 1 4 5 3

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    2    1  4  5  3
die2     2    6    6  5  4  2
die3     3    1    2  4  5  6

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    2    1  4  5  3
die2     2    6    ?  ?  4  2
die3     3    ?    2  ?  5  6

Contradiction

6 4 1 2 5 3

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    4    1  2  5  3
die2     2    ?    ?  ?  2  y
die3     3    ?    y  ?  5  6

6 1 2 4 5 3

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    1    2  4  5  3
die2     2    3    4  ?  4  5
die3     3    2    1  4  5  6

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    1    2  4  5  3
die2     2    4    6  5  4  1
die3     3    2    1  4  5  6

No Contradiction, at least one solution

#4
         zup zdown nw ne sw se
die1     6    ?    ?  x  ?  3
die2     2    ?    ?  ?  x  y
die3     3    ?    y  ?  5  ?



6     [2, 4],  [2, 4],   5, 1,  3

         zup zdown nw ne sw se
die1     6    ?    ?  5  1  3
die2     2    ?    4  1  5  2
die3     3    ?    2  1  5  4
#4 

         zup zdown nw ne sw se
die1     6    ?    ?  5  1  3
die2     2    ?    ?  1  5  z
die3     3    ?    z  1  5  4


Starting over due to figuring out more stuff...

         zup zdown nw ne sw se
die1     6    ?    ?  x  w  3
die2     2    ?    z  w  x  y
die3     3    ?    y  ?  5  z

1. 

         zup zdown nw ne sw se
die1     6    ?    ?  x  2  3
die2     2    ?    z  2  x  y
die3     3    ?    y  ?  5  z

Easy contradiction

2. 
         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    ?    ?  x  1  3
die2     2    ?    6  1  x  y
die3     3    ?    y  ?  5  6

Ambiguous so far, so assume x is 5, x must be 6 because of die 1

         1    1     2 3  3  2
         zup zdown nw ne sw se
die1     6    4    2  5  1  3
die2     2    3    6  1  5  4
die3     3    2    4  1  5  6






