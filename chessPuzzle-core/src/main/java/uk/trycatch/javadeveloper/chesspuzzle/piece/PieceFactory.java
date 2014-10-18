package uk.trycatch.javadeveloper.chesspuzzle.piece;

import uk.trycatch.javadeveloper.chesspuzzle.exception.InvalidPieceException;

/**
 * Factory of chess pieces from PieceType
 *
 * @author troig
 */
public class PieceFactory {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /** Private constructor to force static access to the factory */
   private PieceFactory() {
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Create a chess <tt>Piece</tt> from the <tt>pieceType</tt> passed by parameter.
    *
    * @param pieceType Piece type
    * @return Chess piece instance
    * @throws InvalidPieceException
    * @see PieceType
    */
   public static Piece createPiece(PieceType pieceType) throws InvalidPieceException {
      try {
         return pieceType.getClazz().newInstance();
      } catch (Exception ex) {
         throw new InvalidPieceException();
      }
   }
}
