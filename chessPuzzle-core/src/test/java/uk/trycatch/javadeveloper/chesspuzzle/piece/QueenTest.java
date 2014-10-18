package uk.trycatch.javadeveloper.chesspuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.QUEEN;

/**
 * Test for class <tt>Queen</tt>
 *
 * @author troig
 */
public class QueenTest {

   /**
    * Test method <tt>availableMovementsMask</tt>.<br/>
    * Inputs:<br/>
    * <ul>
    * <li>Board dimensions 4x5</li>
    * <li>Queen on position (1, 1)</li>
    * </ul>
    *
    * @throws Exception Assertion errors
    */
   @Test
   public void testAvailableMovementsMask_4x4_onPos_1_1() throws Exception {
      // Initialize chess board and place a Queen
      ChessBoard chessBoard = new ChessBoard(4, 5);
      Piece queen = PieceFactory.createPiece(QUEEN);
      Position position = new Position(1, 1);
      chessBoard.addPiece(position, queen);

      // Calculate available movements
      BitSet movements = queen.availableMovementsMask(chessBoard, position);

      // Assertions
      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(0, 3);
      movementsExpected.set(5);
      movementsExpected.set(7, 13);
      movementsExpected.set(16);
      movementsExpected.set(18);
      Assert.assertEquals(movements, movementsExpected);
   }
}
