package uk.trycatch.javadeveloper.chesspuzzle.solverservice;

import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.exception.ChessException;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Solution for the puzzle
 *
 * @author troig
 */
public class ChessPuzzleSolution {

   /** Set with all chessBoard solved. Each one with a valid solution for the puzzle */
   private Set<ChessBoard> chessBoardSet;
   /** Time for find puzzle solution */
   private long timeProcess;
   /** Exception thrown by the solver in case of error */
   private ChessException error;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /** Default solution for the puzzle */
   public ChessPuzzleSolution() {
      chessBoardSet = new LinkedHashSet<>();
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns the set with all chessBoard solved. Each one with a valid solution for the puzzle
    *
    * @return Set with all chessBoard solved. Each one with a valid solution for the puzzle
    */
   public Set<ChessBoard> getChessBoardSet() {
      return chessBoardSet;
   }

   /**
    * Add a <tt>ChessBoard</tt> filled with a solution for the puzzle.
    *
    * @param chessBoard <tt>ChessBoard</tt> filled with a solution for the puzzle.
    */
   public synchronized void addChessBoard(ChessBoard chessBoard) {
      chessBoardSet.add(chessBoard);
   }

   /**
    * Return the time spent to find all solutions for the puzzle.
    *
    * @return time spent to find all solutions for the puzzle.
    */
   public long getTimeProcess() {
      return timeProcess;
   }

   /**
    * Set the time spent to find all solutions for the puzzle.
    *
    * @param timeProcess Time spent to find all solutions for the puzzle.
    */
   public void setTimeProcess(long timeProcess) {
      this.timeProcess = timeProcess;
   }

   /**
    * Check if exist any solution for the chess puzzle.
    *
    * @return <tt>true</tt> if exist any solution for the puzzle
    */
   public boolean hasSolution() {
      return !chessBoardSet.isEmpty();
   }

   /**
    * Returns the total number of solutions to the chess puzzle.
    *
    * @return total number of solutions to the chess puzzle.
    */
   public int totalSolutions() {
      return chessBoardSet.size();
   }

   /**
    * Check if the <tt>chessBoard</tt> is contained as a solution.
    *
    * @param chessBoard Chess board with a solution
    * @return <tt>true</tt> if  <tt>chessBoard</tt> is contained as a solution.
    */
   public boolean containsSolution(ChessBoard chessBoard) {
      return chessBoardSet.contains(chessBoard);
   }

   /**
    * Returns the detailed error of the solution.
    *
    * @return Exception thrown by the solver in case of error
    */
   public ChessException getError() {
      return error;
   }

   /**
    * Set the exception thrown by the solver in case of error
    *
    * @param error Exception thrown by the solver in case of error
    */
   public void setError(ChessException error) {
      this.error = error;
   }

   /**
    * Chec if an error has launched trying ot solve the puzzle.
    *
    * @return <tt>true</tt> if an error has launched trying ot solve the puzzle.
    */
   public boolean hasError() {
      return error != null;
   }
}
