package uk.tryCatch.javaDeveloper.chessPuzzle.exception;

/**
 * Exception for scenarios with invalid piece errors.
 *
 * @author troig
 */
public class InvalidPieceException extends Exception {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /** Default constructor for <tt>InvalidPieceException</tt> */
   public InvalidPieceException() {
      super("Invalid piece");
   }
}
