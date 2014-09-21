chessPuzzle
===========

Find all unique configurations of a set of normal chess pieces on  a chess board with dimensions M×N where none of the
pieces is in a position to take any of the others.

Write a program which takes as input:
- The dimensions of the board: M, N.
- The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the board.

As output, the program should list all the unique configurations to the console for which all of the pieces can be
placed on the board without threatening each other.


HOW TO RUN?
Option 1) There are some pre-configured unit tests in module chessPuzzle-core: Class BacktrackChessPuzzleSolverServiceTest
Option 2) Run class ChessPuzzle of module chessPuzzle-console (Basic app launcher where program imputs are passed through command line.
Option 3) TODO: Deploy module chessPuzzle-front on Tomcat (mvn clean install package as war). Open a browser http://localhost:8080/index.htm

NOTES:
- Compiled with jdk 1.8
- Now there is only one implementation with backtracking algorithm (BacktrackChessPuzzleSolverService). Performance
is VERY POOR. TODO Make a better implementation of ChessPuzzleSolverService using heuristics