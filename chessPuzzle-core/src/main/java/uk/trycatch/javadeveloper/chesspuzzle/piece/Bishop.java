package uk.trycatch.javadeveloper.chesspuzzle.piece;

import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

/**
 * Definition of <tt>Bishop</tt> chess piece
 *
 * @author troig
 */
public class Bishop extends Piece {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Bishop</tt>. Sets the piiece type
    */
   public Bishop() {
      super(PieceType.BISHOP);
   }

// -- Methods inherited from abstract class Piece
//--------------------------------------------------------------------------------------------------

   @Override
   public BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos) {
      BitSet movements = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());

      // Diagonoal movements to the right ;
      int maxRowColumn = Math.max(chessBoard.getNumRows(), chessBoard.getNumColums());
      int index = 1;
      for (int i = sourcePos.getX(); i < maxRowColumn; i++) {
         // Riht Up position
         addMovement(chessBoard, sourcePos, movements, index, index);
         // Right down position
         addMovement(chessBoard, sourcePos, movements, index, -index);
         index++;
      }

      // Diagonal movements to the left
      maxRowColumn = Math.max(sourcePos.getX(), sourcePos.getY());
      index = 1;
      for (int i = maxRowColumn; i >= 0; i--) {
         // Left Up position
         addMovement(chessBoard, sourcePos, movements, -index, index);
         // Left Down position
         addMovement(chessBoard, sourcePos, movements, -index, -index);
         index++;
      }
      return movements;
   }
}
