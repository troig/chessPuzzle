package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.Set;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.QUEEN;

/**
 * Definition of <tt>Queen</tt> chess piece
 *
 * @author troig
 */
public class Queen extends Piece {

   /** Queen movements: Rock + Bishop: Final instance of piece Rock to take avantadge of this movememnt engine */
   private final Rock rock = new Rock();
   /** Queen movements: Rock + Bishop: Final instance of piece Bishop to take avantadge of this movememnt engine */
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
   public boolean canAttack(ChessBoard chessBoard, Position source, Position target) {
      // TODO (troig 14/09/14) Let's do it!
      return false;
   }

   @Override
   public Set<Position> availableMovements(ChessBoard chessBoard, Position sourcePos) {
      // TODO (troig 14/09/14) Find a more elegant solution (I don't like have having private instances of rock/bishop
      // Queen movements: Rock + Bishop movements
      Set<Position> positionSet = rock.availableMovements(chessBoard, sourcePos);
      positionSet.addAll(bishop.availableMovements(chessBoard, sourcePos));
      return positionSet;
   }
}
