package uk.tryCatch.javaDeveloper.chessPuzzle.exception;

/**
 * Exception for scenarios with invalid position errors.
 *
 * @author troig
 */
public class InvalidPositionException extends ChessException {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /** Default constructor for <tt>InvalidPieceException</tt> */
   public InvalidPositionException() {
      super("Invalid position");
   }
}
