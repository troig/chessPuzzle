package uk.trycatch.javadeveloper.chesspuzzle.piece;

import junit.framework.Assert;
import org.junit.Test;
import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.board.Position;

import java.util.BitSet;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.ROCK;

/**
 * Test for class <tt>Rock</tt>
 *
 * @author troig
 */
public class RockTest {

   /**
    * Test method <tt>availableMovementsMask</tt>.<br/>
    * Inputs:<br/>
    * <ul>
    * <li>Board dimensions 2x4</li>
    * <li>Rock on position (1, 1)</li>
    * </ul>
    *
    * @throws Exception Assertion errors
    */
   @Test
   public void testAvailableMovementsMask_2x4_onPos_1_1() throws Exception {
      ChessBoard chessBoard = new ChessBoard(2, 4);
      Piece rock = PieceFactory.createPiece(ROCK);
      Position position = new Position(1, 1);
      chessBoard.addPiece(position, rock);

      BitSet movements = rock.availableMovementsMask(chessBoard, position);

      Assert.assertNotNull(movements);

      BitSet movementsExpected = new BitSet(chessBoard.getNumRows() * chessBoard.getNumColums());
      movementsExpected.set(1);
      movementsExpected.set(4);
      movementsExpected.set(6);
      movementsExpected.set(7);
      Assert.assertEquals(movements, movementsExpected);
   }
}
