package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.ROCK;

/**
 * Definition of <tt>Rock</tt> chess piece
 *
 * @author troig
 */
public class Rock extends Piece {

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Rock</tt>. Sets the piiece type
    */
   public Rock() {
      super(ROCK);
   }

// -- Methods inherited from abstract class Piece
//--------------------------------------------------------------------------------------------------

   @Override
   public BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos) {
      // Binary mask representation for the available rock movements from sourcePos
      BitSet movmentMask = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());

      // Movements over X coordinate (rows)
      for (int row = 0; row < chessBoard.getNumRows(); row++) {
         Position rowPosition = new Position(row, sourcePos.getColumn());
         movmentMask.set(rowPosition.getRow() + chessBoard.getNumColums() * rowPosition.getColumn());

      }
      // Movements over Y coordinate (columns)
      for (int column = 0; column < chessBoard.getNumColums(); column++) {
         Position rowPosition = new Position(sourcePos.getRow(), column);
         movmentMask.set(rowPosition.getRow() + chessBoard.getNumColums() * rowPosition.getColumn());
      }

      // Exclude the source position
      movmentMask.clear(sourcePos.getRow() + chessBoard.getNumColums() * sourcePos.getColumn());
      return movmentMask;
   }
}
