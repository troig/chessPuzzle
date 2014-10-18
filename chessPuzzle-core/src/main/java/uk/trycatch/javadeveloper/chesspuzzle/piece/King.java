package uk.trycatch.javadeveloper.chesspuzzle.piece;

import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.KING;

/**
 * Definition of <tt>King</tt> chess piece
 *
 * @author troig
 */
public class King extends Piece {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>King</tt>. Sets the piiece type
    */
   public King() {
      super(KING);
   }

// -- Methods inherited from abstract class Piece
//--------------------------------------------------------------------------------------------------

   @Override
   public BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos) {
      BitSet movements = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      // King can move one position over x/y and diagonal
      addMovement(chessBoard, sourcePos, movements, 0, 1);
      addMovement(chessBoard, sourcePos, movements, 1, 1);
      addMovement(chessBoard, sourcePos, movements, 1, 0);
      addMovement(chessBoard, sourcePos, movements, 1, -1);
      addMovement(chessBoard, sourcePos, movements, 0, -1);
      addMovement(chessBoard, sourcePos, movements, -1, -1);
      addMovement(chessBoard, sourcePos, movements, -1, 0);
      addMovement(chessBoard, sourcePos, movements, -1, 1);

      return movements;
   }
}
