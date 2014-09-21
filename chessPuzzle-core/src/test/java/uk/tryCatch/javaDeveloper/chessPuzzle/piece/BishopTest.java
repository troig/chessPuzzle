package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.BISHOP;

/**
 * Test for class <tt>Bishop</tt>
 *
 * @author troig
 */
public class BishopTest {

   /**
    * Test method <tt>availableMovementsMask</tt>.<br/>
    * Inputs:<br/>
    * <ul>
    * <li>Board dimensions 3x3</li>
    * <li>Bishop on position (1, 1)</li>
    * </ul>
    *
    * @throws Exception Assertion errors
    */
   @Test
   public void testAvailableMovementsMask_3x3_onPos_1_1() throws Exception {
      // Initialize chess board and place a bishop
      ChessBoard chessBoard = new ChessBoard(3, 3);
      Piece bishop = PieceFactory.createPiece(BISHOP);
      Position position = new Position(1, 1);
      chessBoard.addPiece(position, bishop);

      // Calculate available movements
      BitSet movements = bishop.availableMovementsMask(chessBoard, position);

      // Assertions
      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(0);
      movementsExpected.set(2);
      movementsExpected.set(6);
      movementsExpected.set(8);
      Assert.assertEquals(movements, movementsExpected);
   }
}
