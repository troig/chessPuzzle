package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.KING;
import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.KNIGHT;

/**
 * Test for class <tt>Knight</tt>
 *
 * @author troig
 */
public class KnightTest {

   /**
    * Test method <tt>availableMovementsMask</tt>.<br/>
    * Inputs:<br/>
    * <ul>
    * <li>Board dimensions 4x4</li>
    * <li>Knight on position (2, 2)</li>
    * </ul>
    *
    * @throws Exception Assertion errors
    */
   @Test
   public void testAvailableMovementsMask_4x4_onPos_2_2() throws Exception {
      // Initialize chess board and place a Knight
      ChessBoard chessBoard = new ChessBoard(4, 4);
      Piece knight = PieceFactory.createPiece(KNIGHT);
      Position position = new Position(2, 2);
      chessBoard.addPiece(position, knight);

      // Calculate available movements
      BitSet movements = knight.availableMovementsMask(chessBoard, position);

      // Assertions
      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(1);
      movementsExpected.set(3);
      movementsExpected.set(4);
      movementsExpected.set(12);
      Assert.assertEquals(movements, movementsExpected);
   }
}
