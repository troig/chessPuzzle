package uk.trycatch.javadeveloper.chesspuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.BISHOP;

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
      ChessBoard chessBoard = new ChessBoard(3, 3);
      Piece bishop = PieceFactory.createPiece(BISHOP);
      Position position = new Position(1, 1);
      chessBoard.addPiece(position, bishop);

      BitSet movements = bishop.availableMovementsMask(chessBoard, position);

      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(0);
      movementsExpected.set(2);
      movementsExpected.set(6);
      movementsExpected.set(8);
      Assert.assertEquals(movements, movementsExpected);
   }
}
