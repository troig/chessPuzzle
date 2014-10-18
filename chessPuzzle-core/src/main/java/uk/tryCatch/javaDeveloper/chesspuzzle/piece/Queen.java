package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.QUEEN;

/**
 * Definition of <tt>Queen</tt> chess piece
 *
 * @author troig
 */
public class Queen extends Piece {

   /** Queen movements: Rock + Bishop. Final instance of piece Rock to take avantadge of this movememnt engine */
   private final Rock rock = new Rock();
   /** Queen movements: Rock + Bishop. Final instance of piece Bishop to take avantadge of this movememnt engine */
   private final Bishop bishop = new Bishop();

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Bishop</tt>. Sets the piiece type
    */
   public Queen() {
      super(QUEEN);
   }

// -- Methods inherited from abstract class Piece
//--------------------------------------------------------------------------------------------------

   @Override
   public BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos) {
      // Queen movements: Rock + Bishop movements
      BitSet rockMovementMask = rock.availableMovementsMask(chessBoard, sourcePos);
      BitSet bishopMovementMask = bishop.availableMovementsMask(chessBoard, sourcePos);

      rockMovementMask.or(bishopMovementMask);
      return rockMovementMask;
   }
}
