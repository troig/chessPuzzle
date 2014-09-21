package uk.tryCatch.javaDeveloper.chessPuzzle.solverService.impl;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;
import uk.tryCatch.javaDeveloper.chessPuzzle.exception.ChessException;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.Piece;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverService.ChessPuzzleSolution;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverService.ChessPuzzleSolverService;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverService.PieceConfiguration;

import java.util.List;

/**
 * Implementation of <tt>ChessPuzzleSolverService</tt> based on <tt>Backtracking</tt> algorithm.
 *
 * @author troig
 */
public class BacktrackChessPuzzleSolverService implements ChessPuzzleSolverService {

// -- Methods inherited from interface ChessPuzzleSolverService
//--------------------------------------------------------------------------------------------------

   /**
    * Find all unique configurations of a set of normal chess pieces (Queen, Bishop, Rock, Knight and King) on a chess
    * board with dimensions <tt>numRows</tt> x <tt>numColumns </tt> where none of the pieces is in a position to take
    * any of the others.<p></p>
    * This solver is based on <tt>Backtracking</tt> recursion algorithm.
    *
    * @param numRowsBoard       Number of rows of the chess board
    * @param numColumsBoard     Number of columns of the chess board
    * @param pieceConfiguration Piece configuration for the puzzle (X pieces each certain piece type)
    * @return Solution for the puzzle (N chessBoard solved, timeProces...)
    * @throws ChessException Solver errros
    */
   @Override
   public ChessPuzzleSolution solve(int numRowsBoard, int numColumsBoard,
                                    PieceConfiguration pieceConfiguration) throws ChessException {

      long initProcess = System.currentTimeMillis();
      ChessPuzzleSolution solution = new ChessPuzzleSolution();
      try {
         // No pieces to place: We can return
         if (pieceConfiguration.getTotalNumberOfPieces() == 0) throw new ChessException("Any piece to place");

         // 1. Create and initialize the ChessBoard
         ChessBoard chessBoard = new ChessBoard(numRowsBoard, numColumsBoard);

         // 2. Try to solve the puzzle with backtracking
         solve(chessBoard,                                  // Chess board to solve
               0,                                           // Index to the position to start to chech a solution
               pieceConfiguration.getPieceList(),           // List of pieces to place on the chess board
               pieceConfiguration.getTotalNumberOfPieces(), // Number total of pieces to place on the chess board
               solution);                                   // Solution collector

      } catch (Exception e) {
         // Unexpected error has produced trying to solve the puzzle. Add an erro to the solution
         solution.setError(new ChessException("Error trying to solve the puzzle: " + e.getMessage()));
      }

      // Finally, set the time to proces
      solution.setTimeProcess(System.currentTimeMillis() - initProcess);

      return solution;
   }

// -- Private method
//--------------------------------------------------------------------------------------------------

   /**
    * Find with <tt>Backtracking</tt> algorithm all unique configurations of a set of normal chess pieces (Queen,
    * Bishop, Rock, Knight and King) on the  <tt>chessBoard</tt> where none of the pieces is attacked by others. Us
    *
    * @param chessBoard   Chess Board
    * @param posIndex     Index to the position checked
    * @param pieceList    List of pieces to place on the chess board
    * @param pieceToPlace How many pieces are not yet placed
    * @param solution     solution colector
    * @return <tt>true</tt> if chess board is solved
    * @throws Exception Can throw runtime errors
    */
   private boolean solve(ChessBoard chessBoard, int posIndex, List<Piece> pieceList, int pieceToPlace,
                         ChessPuzzleSolution solution) throws Exception {

      // Retrieval all positions of the board to iterate and check all of them
      List<Position> allPosition = chessBoard.getAllPosition();

      // If all pieces are placed, a solution is found.
      if (pieceToPlace == 0) {
         ChessBoard chessBoardSolved = chessBoard.slimCopyOf();
         if (!solution.containsSolution(chessBoardSolved)) {
            solution.addChessBoard(chessBoardSolved);
         }

         // All positions of the board are checkd -> Return
         if (posIndex == allPosition.size()) {
            return true;
         }
      }

      // Iterate over all positions of the board. Place a piece if it's posible, backtracking if piece will be attacked
      // at this position
      for (int i = posIndex; i < allPosition.size(); i++) {
         Position position = allPosition.get(i);
         int numPiece = pieceToPlace - 1;

         // Break recursion if has no more pieces
         if (numPiece < 0) return false;

         Piece piece = pieceList.get(numPiece);
         // Piece can be placed is cell is empty and cannot be attacked from any other piece on the board
         if (chessBoard.isEmpty(position) && chessBoard.canBePlacedNonAttack(piece, position)) {
            // Place piece on the chess board
            chessBoard.addPiece(position, piece);

            // Backtracking
            if (solve(chessBoard, posIndex + 1, pieceList, pieceToPlace - 1, solution)) {
               return true;
            }
            chessBoard.removePiece(position);
         }
      }

      return false;
   }
}
