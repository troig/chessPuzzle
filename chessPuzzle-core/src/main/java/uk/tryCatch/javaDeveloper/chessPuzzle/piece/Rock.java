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

      // Movements over X coordinate
      for (int x = 0; x < chessBoard.getNumColums(); x++) {
         Position rowPosition = new Position(x, sourcePos.getY());
         movmentMask.set(rowPosition.getX() + chessBoard.getNumColums() * rowPosition.getY());

      }
      // Movements over Y coordinate
      for (int y = 0; y < chessBoard.getNumRows(); y++) {
         Position rowPosition = new Position(sourcePos.getX(), y);
         movmentMask.set(rowPosition.getX() + chessBoard.getNumColums() * rowPosition.getY());
      }

      // Exclude the source position
      movmentMask.clear(sourcePos.getX() + chessBoard.getNumColums() * sourcePos.getY());
      return movmentMask;
   }
}
