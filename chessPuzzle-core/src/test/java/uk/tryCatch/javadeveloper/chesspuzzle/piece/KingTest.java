package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.KING;
import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.ROCK;

/**
 * Test for class <tt>King</tt>
 *
 * @author troig
 */
public class KingTest {

   /**
    * Test method <tt>availableMovementsMask</tt>.<br/>
    * Inputs:<br/>
    * <ul>
    * <li>Board dimensions 3x2</li>
    * <li>King on position (0, 1)</li>
    * </ul>
    *
    * @throws Exception Assertion errors
    */
   @Test
   public void testAvailableMovementsMask_3x2_onPos_0_1() throws Exception {
      // Initialize chess board and place a king
      ChessBoard chessBoard = new ChessBoard(3, 2);
      Piece king = PieceFactory.createPiece(KING);
      Position position = new Position(0, 1);
      chessBoard.addPiece(position, king);

      // Calculate available movements
      BitSet movements = king.availableMovementsMask(chessBoard, position);

      // Assertions
      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(0);
      movementsExpected.set(1);
      movementsExpected.set(3);
      movementsExpected.set(4);
      movementsExpected.set(5);
      Assert.assertEquals(movements, movementsExpected);
   }
}
