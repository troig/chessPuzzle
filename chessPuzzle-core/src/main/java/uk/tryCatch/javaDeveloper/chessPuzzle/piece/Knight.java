package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.Set;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.KNIGHT;

/**
 * Definition of <tt>Knight</tt> chess piece
 *
 * @author troig
 */
public class Knight extends Piece {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Knight</tt>. Sets the piiece type
    */
   public Knight() {
      super(KNIGHT);
   }

// -- Methods inherited from abstract class Piece
//--------------------------------------------------------------------------------------------------

   @Override
   public boolean canAttack(ChessBoard chessBoard, Position source, Position target) {
      // TODO (troig 14/09/14) Let's do it!
      return false;
   }

   /**
    * Return a set with all <tt>Bishop</tt> available movements for given <tt>chessBoard</tt> and <tt>sourcePos</tt>.
    *
    * @param chessBoard Chess board
    * @param sourcePos  Source position
    * @return All <tt>Bishop</tt> available movements for given <tt>chessBoard</tt> and <tt>sourcePos</tt>.
    */
   @Override
   public Set<Position> availableMovements(ChessBoard chessBoard, Position sourcePos) {
      // TODO (troig 14/09/14) Let's do it!
      return null;
   }
}
