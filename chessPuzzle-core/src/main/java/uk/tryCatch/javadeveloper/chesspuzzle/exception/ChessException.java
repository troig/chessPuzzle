package uk.tryCatch.javaDeveloper.chessPuzzle.exception;

/**
 * General exception used in chessPuzzle
 *
 * @author troig
 */
public class ChessException extends Exception {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>ChessException</tt>
    *
    * @param message Exception message
    */
   public ChessException(String message) {
      super(message);
   }
}
