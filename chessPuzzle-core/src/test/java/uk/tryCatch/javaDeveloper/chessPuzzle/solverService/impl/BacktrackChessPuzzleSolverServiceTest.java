package uk.tryCatch.javaDeveloper.chessPuzzle.solverService.impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverService.ChessPuzzleSolution;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverService.PieceConfiguration;

import java.util.HashSet;
import java.util.Set;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.QUEEN;

/**
 * Test class for <tt>BacktrackChessPuzzleSolverService</tt> implementation
 *
 * @author troig
 */
public class BacktrackChessPuzzleSolverServiceTest {

   /** Solver service backtracking implementation */
   private static BacktrackChessPuzzleSolverService solverService;

   @BeforeClass
   public static void setUp() throws Exception {
      solverService = new BacktrackChessPuzzleSolverService();
   }

   /**
    * Test method <tt>solve</tt> for imputs:
    * <ul>
    * <li>4x4 board</li>
    * <li>4 Queens</li>
    * </ul>
    * Output: 2 solutions
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_4x4_4Q_2Solutions() throws Exception {
      // Piece configuraiton input
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 4);

      // Try to solve
      ChessPuzzleSolution solution = solverService.solve(4, 4, pieceConfiguration);

      // Assertion available solutions
      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(2, solution.totalSolutions());

      Set<String> setChessBoardKeySolvedExpected = new HashSet<>();
      setChessBoardKeySolvedExpected.add("**Q*Q******Q*Q**");
      setChessBoardKeySolvedExpected.add("*Q*****QQ*****Q*");

      Set<ChessBoard> chessBoardSolvedSet = solution.getChessBoardSet();
      for (ChessBoard chessBoardSolved : chessBoardSolvedSet) {
         setChessBoardKeySolvedExpected.remove(chessBoardSolved.createChessBoardKey(chessBoardSolved));
      }
      // Check all solution has found
      Assert.assertEquals(0, setChessBoardKeySolvedExpected.size());
   }

   /**
    * Test method <tt>solve</tt> for imputs:
    * <ul>
    * <li>3x4 board</li>
    * <li>2 Queens</li>
    * </ul>
    * Output: 20 solutions
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_3x4_2Q_20Solutions() throws Exception {
      // Piece configuraiton input
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 2);

      // Try to solve
      ChessPuzzleSolution solution = solverService.solve(3, 4, pieceConfiguration);

      // Assertion available solutions
      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(20, solution.totalSolutions());
   }


}
