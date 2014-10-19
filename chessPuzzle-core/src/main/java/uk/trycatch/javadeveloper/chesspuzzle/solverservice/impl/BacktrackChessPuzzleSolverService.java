package uk.trycatch.javadeveloper.chesspuzzle.solverservice.impl;

import com.google.common.collect.Collections2;
import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;
import uk.trycatch.javadeveloper.chesspuzzle.exception.ChessException;
import uk.trycatch.javadeveloper.chesspuzzle.piece.Piece;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.ChessPuzzleSolution;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.ChessPuzzleSolverService;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.PieceConfiguration;

import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of <tt>ChessPuzzleSolverService</tt> based on <tt>Backtracking</tt> algorithm.
 *
 * @author troig
 */
public class BacktrackChessPuzzleSolverService implements ChessPuzzleSolverService {

   /** Number threads used to find all possible solutions */
   private static final int numThreadsProcess = 10;
   /** Logger */
   private static final Logger LOGGER = Logger.getLogger( BacktrackChessPuzzleSolverService.class.getName() );


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
    * @return Solution for the puzzle (N chessBoard solved, timeProcess...)
    * @throws ChessException Solver erros
    */
   @Override
   public ChessPuzzleSolution solve(int numRowsBoard, int numColumsBoard,
                                    PieceConfiguration pieceConfiguration) throws ChessException {

      long initProcess = System.currentTimeMillis();
      ChessPuzzleSolution solution = new ChessPuzzleSolution();
      try {
         // No pieces to place: We can return
         if (pieceConfiguration.getTotalNumberOfPieces() == 0) throw new ChessException("Any piece to place");
         if (pieceConfiguration.getTotalNumberOfPieces() > 6) throw new ChessException("Maximum 6 pieces to place");

         // 1. Create and initialize the ChessBoard
         ChessBoard chessBoard = new ChessBoard(numRowsBoard, numColumsBoard);

         List<Piece> pieceList = pieceConfiguration.getPieceList();

         // Create a stack with all possible piece configuration. (Ex: QBR -> QBR, QRB, BQR, BRQ, RQB, RBQ)
         /** TODO (troig 21/09/2014): Very poor performance solution. Don't use all combinations, only which are really needed. */
         /** TODO (troig 21/09/2014): Make heuristics to determine best cell to try to place every piece. */
         Stack<List<Piece>> pieceListCombinationStack = new Stack<>();
         pieceListCombinationStack.addAll(Collections2.orderedPermutations(pieceList));


         // 2. N process try to solve the puzzle for different piece configuration combination
         int numThreads = numThreadsProcess < pieceListCombinationStack.size()
               ? numThreadsProcess
               : pieceListCombinationStack.size();

         Thread[] arrayThread = new Thread[numThreads];
         for (int i = 0; i < numThreads; i++) {
            arrayThread[i] = new SolverThread((ChessBoard)chessBoard.clone(), pieceListCombinationStack, solution);
            arrayThread[i].start();
         }

         for (int i = 0; i < numThreads; i++) {
            arrayThread[i].join();
         }

      } catch (Exception e) {
         e.printStackTrace();
         // Unexpected error has produced trying to solve the puzzle. Add an erro to the solution
         solution.setError(new ChessException("Error trying to solve the puzzle: " + e.getMessage()));
      }

      // Finally, set the time to process
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
    * @param solution     solution collector
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

         // All positions of the board are checked -> Return
         if (posIndex == allPosition.size()) {
            return true;
         }
      }

      // Iterate over all positions of the board. Place a piece if it's possible, backtracking if piece will be attacked
      // at this position
      for (int i = posIndex; i < allPosition.size(); i++) {
         Position position = allPosition.get(i);
         int numPiece = pieceToPlace - 1;

         // Break recursion if has no more pieces
         if (numPiece < 0) return false;

         Piece piece = pieceList.get(numPiece);
         // Piece can be placed is cell is empty and cannot be attacked from any other piece on the board
         if (chessBoard.canBePlacedNonAttack(piece, position)) {
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

   @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
   /**
    * Thread to solve the puzzle.
    */
   private class SolverThread extends Thread {
      /** Chess board */
      private ChessBoard chessBoard;
      /** Stack with all combinations of piece configuration */
      private Stack<List<Piece>> pieceListCombinationStack;
      /** Puzzle solution collector */
      private ChessPuzzleSolution solution;

      /**
       * Constutor of <tt>SolverThread</tt>
       *
       * @param chessBoard                Chess Board
       * @param pieceListCombinationStack Stack with all combinations of piece configuration
       * @param solution                  Puzzle solution collector
       * @throws Exception Unexpected errors trying to solve the puzzle
       */
      private SolverThread(ChessBoard chessBoard,
                           Stack<List<Piece>> pieceListCombinationStack,
                           ChessPuzzleSolution solution) throws Exception {
         this.chessBoard = chessBoard;
         this.pieceListCombinationStack = pieceListCombinationStack;
         this.solution = solution;
      }

      @SuppressWarnings("SynchronizeOnNonFinalField")
      @Override
      public void run() {
         try {
            while (!pieceListCombinationStack.isEmpty()) {
               List<Piece> pieceList;
               synchronized (pieceListCombinationStack) {
                  pieceList = pieceListCombinationStack.pop();
               }
               if (pieceList != null) {
                  solve(chessBoard,       // Chess board to solve
                        0,                // Index to the position to start to check a solution
                        pieceList,        // List of pieces to place on the chess board
                        pieceList.size(), // Number total of pieces to place on the chess board
                        solution);        // Solution collector
               }
            }
         } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error");
         }
      }
   }
}
