package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.HashSet;
import java.util.Set;

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
      // TODO (troig 14/09/14) Review and optimize (make a method with param right/left)
      Set<Position> availPositionSet = new HashSet<>();

      // Diagonoal movements to the right ;
      int maxRowColumn = Math.max(chessBoard.getNumRows(), chessBoard.getNumColums());
      int index = 1;
      for (int i = sourcePos.getRow(); i < maxRowColumn; i++) {
         // Riht Up position
         Position positionUp = new Position(sourcePos.getRow() + index, sourcePos.getColumn() + index);
         if (chessBoard.contains(positionUp) && !positionUp.equals(sourcePos)) availPositionSet.add(positionUp);

         // Right down position
         Position positionDown = new Position(sourcePos.getRow() + index, sourcePos.getColumn() - index);
         if (chessBoard.contains(positionDown) && !positionDown.equals(sourcePos)) availPositionSet.add(positionDown);
         index++;
      }

      // Diagonal movements to the left
      maxRowColumn = Math.max(sourcePos.getRow(), sourcePos.getColumn());
      index = 1;
      for (int i = maxRowColumn; i >= 0; i--) {
         // Left Up position
         Position positionUp = new Position(sourcePos.getRow() - index, sourcePos.getColumn() + index);
         if (chessBoard.contains(positionUp) && !positionUp.equals(sourcePos)) availPositionSet.add(positionUp);
         // Left Down position
         Position positionDown = new Position(sourcePos.getRow() - index, sourcePos.getColumn() - index);
         if (chessBoard.contains(positionDown) && !positionDown.equals(sourcePos)) availPositionSet.add(positionDown);
         index++;
      }

      return availPositionSet;
   }
}
