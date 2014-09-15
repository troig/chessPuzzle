package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.HashSet;
import java.util.Set;

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
   public boolean canAttack(ChessBoard chessBoard, Position source, Position target) {
      // TODO (troig 14/09/14) Let's do it!
      return false;
   }

   @Override
   public Set<Position> availableMovements(ChessBoard chessBoard, Position sourcePos) {
      // TODO (troig 14/09/14) Review and optimize
      Set<Position> availPositionSet = new HashSet<>();

      // Movements over X coordinate (rows)
      for (int row = 0; row < chessBoard.getNumRows(); row++) {
         Position rowPosition = new Position(row, sourcePos.getColumn());
         if (!rowPosition.equals(sourcePos)) availPositionSet.add(rowPosition);
      }
      // Movements over Y coordinate (columns)
      for (int column = 0; column < chessBoard.getNumColums(); column++) {
         Position rowPosition = new Position(sourcePos.getRow(), column);
         if (!rowPosition.equals(sourcePos)) availPositionSet.add(rowPosition);
      }

      return availPositionSet;
   }
}
