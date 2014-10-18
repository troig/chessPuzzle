package uk.trycatch.javadeveloper.chesspuzzle.piece;

import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.KNIGHT;

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
   public BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos) {
      BitSet movements = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      // Knight can move making 'L' : 2 + 1 positions
      addMovement(chessBoard, sourcePos, movements, -2, 1);
      addMovement(chessBoard, sourcePos, movements, -1, 2);
      addMovement(chessBoard, sourcePos, movements, 1, 2);
      addMovement(chessBoard, sourcePos, movements, 2, 1);
      addMovement(chessBoard, sourcePos, movements, 2, -1);
      addMovement(chessBoard, sourcePos, movements, 1, -2);
      addMovement(chessBoard, sourcePos, movements, -1, -2);
      addMovement(chessBoard, sourcePos, movements, -2, -1);

      return movements;
   }
}
