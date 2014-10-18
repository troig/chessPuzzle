package uk.trycatch.javadeveloper.chesspuzzle.board;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.trycatch.javadeveloper.chesspuzzle.exception.InvalidPositionException;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceFactory.createPiece;
import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.*;

/**
 * Test for class <tt>ChessBoard</tt>
 *
 * @author troig
 */
public class ChessBoardTest {

   /** Chess board with some pieces placed to test purpose */
   private static ChessBoard chessBoard;

   /**
    * Initialize a ChessBoard with this configuration:
    * <pre>
    *    * * * * *
    *    * * * L B
    *    * * * * *
    *    Q * * * *
    * </pre>
    *
    * @throws Exception
    */
   @BeforeClass
   public static void setUp() throws Exception {
      chessBoard = new ChessBoard(4, 5);
      chessBoard.addPiece(new Position(0, 0), createPiece(QUEEN));
      chessBoard.addPiece(new Position(3, 2), createPiece(KNIGHT));
      chessBoard.addPiece(new Position(4, 2), createPiece(BISHOP));
   }

   /**
    * Test method <tt>testCanBePlacedNonAttack</tt>.
    * Place a rock on position (2, 1): Return <tt>true</tt> (Rock is safe and no attack other piece)
    *
    * @throws Exception Test errors
    */
   @Test
   public void testCanBePlacedNonAttack_Rock_true() throws Exception {
      boolean canBePlaced = chessBoard.canBePlacedNonAttack(createPiece(ROCK), new Position(2, 1));
      Assert.assertTrue(canBePlaced);
   }

   /**
    * Test method <tt>testCanBePlacedNonAttack</tt>.
    * Place a king on position (1, 1): Return <tt>false</tt> (King can attach queen on position (0, 0). Other pieces
    * placed can attack the king too)
    *
    * @throws Exception Test errors
    */
   @Test
   public void testCanBePlacedNonAttack_King_false() throws Exception {
      boolean canBePlaced = chessBoard.canBePlacedNonAttack(createPiece(KING), new Position(1, 1));
      Assert.assertFalse(canBePlaced);
   }

   /**
    * Test method <tt>testCanBePlacedNonAttack</tt>.
    * Throws <tt>InvalidPieceException</tt> when input position is out of chessBoard
    *
    * @throws Exception Test errors
    */
   @Test(expected = InvalidPositionException.class)
   public void testCanBePlacedNonAttack_ThrowsInvalidPositionException() throws Exception {
      chessBoard.canBePlacedNonAttack(createPiece(KING), new Position(6, 4));
   }
}
