package uk.trycatch.javadeveloper.chesspuzzle.solverservice.impl;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import uk.trycatch.javadeveloper.chesspuzzle.Performance;
import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.ChessPuzzleSolution;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.PieceConfiguration;

import java.util.HashSet;
import java.util.Set;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.*;

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
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>3x3 board</li>
    * <li>2 King</li>
    * <li>1 Rock</li>
    * </ul>
    * Output: 4 solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_3x3_2K_1R_4Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(KING, 2);
      pieceConfiguration.addPieces(ROCK, 1);

      ChessPuzzleSolution solution = solverService.solve(3, 3, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(4, solution.totalSolutions());

      Set<String> setChessBoardKeySolvedExpected = new HashSet<>();
      setChessBoardKeySolvedExpected.add("K*K****R*");
      setChessBoardKeySolvedExpected.add("**KR****K");
      setChessBoardKeySolvedExpected.add("K****RK**");
      setChessBoardKeySolvedExpected.add("*R****K*K");

      Set<ChessBoard> chessBoardSolvedSet = solution.getChessBoardSet();
      for (ChessBoard chessBoardSolved : chessBoardSolvedSet) {
         setChessBoardKeySolvedExpected.remove(chessBoardSolved.createChessBoardKey(chessBoardSolved));
      }
      Assert.assertEquals(0, setChessBoardKeySolvedExpected.size());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>4x4 board</li>
    * <li>2 Rooks</li>
    * <li>4 Knights</li>
    * </ul>
    * Output: 8 solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_4x4_2R_4L_8Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(ROCK, 2);
      pieceConfiguration.addPieces(KNIGHT, 4);
 
      ChessPuzzleSolution solution = solverService.solve(4, 4, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(8, solution.totalSolutions());

      Set<String> setChessBoardKeySolvedExpected = new HashSet<>();
      setChessBoardKeySolvedExpected.add("*L*L**R**L*LR***");
      setChessBoardKeySolvedExpected.add("L*L****RL*L**R**");
      setChessBoardKeySolvedExpected.add("*L*LR****L*L**R*");
      setChessBoardKeySolvedExpected.add("L*L**R**L*L****R");
      setChessBoardKeySolvedExpected.add("**R**L*LR****L*L");
      setChessBoardKeySolvedExpected.add("R****L*L**R**L*L");
      setChessBoardKeySolvedExpected.add("***RL*L**R**L*L*");
      setChessBoardKeySolvedExpected.add("*R**L*L****RL*L*");

      Set<ChessBoard> chessBoardSolvedSet = solution.getChessBoardSet();
      for (ChessBoard chessBoardSolved : chessBoardSolvedSet) {
         setChessBoardKeySolvedExpected.remove(chessBoardSolved.createChessBoardKey(chessBoardSolved));
      }
      Assert.assertEquals(0, setChessBoardKeySolvedExpected.size());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>4x4 board</li>
    * <li>4 Queens</li>
    * </ul>
    * Output: 2 solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_4x4_4Q_2Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 4);

      ChessPuzzleSolution solution = solverService.solve(4, 4, pieceConfiguration);

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
      Assert.assertEquals(0, setChessBoardKeySolvedExpected.size());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>3x4 board</li>
    * <li>2 Queens</li>
    * </ul>
    * Output: 20 solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_3x4_2Q_20Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 2);

      ChessPuzzleSolution solution = solverService.solve(3, 4, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(20, solution.totalSolutions());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>3x3 board</li>
    * <li>1 Queens</li>
    * <li>1 Knight</li>
    * <li>1 Rock</li>
    * <li>1 King</li>
    * </ul>
    * Output: No solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_3x3_1Q_1L_1R_1K_NoSolutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 1);
      pieceConfiguration.addPieces(KNIGHT, 1);
      pieceConfiguration.addPieces(ROCK, 1);
      pieceConfiguration.addPieces(KING, 1);

      ChessPuzzleSolution solution = solverService.solve(3, 3, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertFalse(solution.hasSolution());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>4x4 board</li>
    * <li>1 Queens</li>
    * <li>1 Knight</li>
    * <li>1 Rock</li>
    * </ul>
    * Output: 48 solutions
    *
    * @throws Exception Test errors
    */
   @Test
   public void testSolve_4x4_1Q_1L_1R_48Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 1);
      pieceConfiguration.addPieces(KNIGHT, 1);
      pieceConfiguration.addPieces(ROCK, 1);

      ChessPuzzleSolution solution = solverService.solve(4, 4, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(48, solution.totalSolutions());
   }

   /**
    * Test method <tt>solve</tt> for inputs:
    * <ul>
    * <li>6X6 board</li>
    * <li>1 Queens</li>
    * <li>1 Bishop</li>
    * <li>1 Knight</li>
    * <li>1 King</li>
    * </ul>
    * Output: 54208 solutions
    *
    * @throws Exception Test errors
    */
   @Category(Performance.class)
   @Test(timeout = 7500)
   public void testSolve_6x6_1Q_1_B_1L_1K_4_54208Solutions() throws Exception {
      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, 1);
      pieceConfiguration.addPieces(BISHOP, 1);
      pieceConfiguration.addPieces(KNIGHT, 1);
      pieceConfiguration.addPieces(KING, 1);

      ChessPuzzleSolution solution = solverService.solve(6, 6, pieceConfiguration);

      Assert.assertNotNull(solution);
      Assert.assertFalse(solution.hasError());
      Assert.assertTrue(solution.hasSolution());
      Assert.assertEquals(54208, solution.totalSolutions());
   }
}
