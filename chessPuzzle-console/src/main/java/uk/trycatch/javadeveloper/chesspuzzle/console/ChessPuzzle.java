package uk.trycatch.javadeveloper.chesspuzzle.console;

import uk.trycatch.javadeveloper.chesspuzzle.board.ChessBoard;
import uk.trycatch.javadeveloper.chesspuzzle.console.logger.SimpleFormatter;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.ChessPuzzleSolution;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.ChessPuzzleSolverService;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.PieceConfiguration;
import uk.trycatch.javadeveloper.chesspuzzle.solverservice.impl.BacktrackChessPuzzleSolverService;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static uk.trycatch.javadeveloper.chesspuzzle.piece.PieceType.*;

/**
 * Basic console application to solve the chess puzzle.<br/>
 * Inputs are passed like console arguments and the possible solutions are shown in plain text mode.
 *
 * @author troig
 */
public class ChessPuzzle {

   /** Logger */
   private static final Logger LOGGER = Logger.getLogger(ChessPuzzle.class.getName());

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /** Launch the app */
   public static void main(String[] args) throws Exception {
      // Inputs passed by command line
      Scanner scanner = new Scanner(System.in);
      ConsoleHandler consoleHandler = new ConsoleHandler();
      consoleHandler.setFormatter(new SimpleFormatter());
      LOGGER.setUseParentHandlers(false);
      LOGGER.addHandler(consoleHandler);

      LOGGER.info("Welcome to chess puzzle solver.\n");

      // Board dimensions
      int numRowsBoard = scanNumber(scanner, "board rows", 2, 7);
      int numColumnsBoard = scanNumber(scanner, "board columns", 2, 7);

      // Piece configuration
      int numQueens = scanNumber(scanner, "queens", 0, 2);
      int numBishop = scanNumber(scanner, "bishops", 0, 2);
      int numRock = scanNumber(scanner, "rocks", 0, 2);
      int numKnight = scanNumber(scanner, "knights", 0, 2);
      int numKing = scanNumber(scanner, "kings", 0, 2);

      PieceConfiguration pieceConfiguration = new PieceConfiguration();
      pieceConfiguration.addPieces(QUEEN, numQueens);
      pieceConfiguration.addPieces(BISHOP, numBishop);
      pieceConfiguration.addPieces(ROCK, numRock);
      pieceConfiguration.addPieces(KNIGHT, numKnight);
      pieceConfiguration.addPieces(KING, numKing);

      ChessPuzzleSolverService solverService = new BacktrackChessPuzzleSolverService();
      ChessPuzzleSolution solution = solverService.solve(numRowsBoard, numColumnsBoard, pieceConfiguration);

      // Output available solutions
      if (solution.hasError()) {
         LOGGER.info(solution.getError().getMessage());
      } else {
         LOGGER.info("\nTotal solutions: " + solution.totalSolutions());
         LOGGER.info("\nTime process   : " + solution.getTimeProcess() + " ms");

         if (solution.hasSolution()) {
            int numSolution = 1;
            for (ChessBoard chessBoard : solution.getChessBoardSet()) {
               LOGGER.info("Solution " + numSolution++ + ": \n");
               LOGGER.info(chessBoard.toString());
            }
         }
      }
   }
// -- Private methods
//--------------------------------------------------------------------------------------------------

   /**
    * Scann and returns a valid number  (between [<tt>minimAllowed</tt>, <tt>maximAllowed</tt>] passed through command
    * line.
    *
    * @param scanner      Command line scanner
    * @param element      Element requested ("queens", "bishops", ...)
    * @param minimAllowed Minim valid number
    * @param maximAllowed Maxim valid number
    * @return valid number passed by command line
    */
   private static int scanNumber(Scanner scanner, String element, int minimAllowed, int maximAllowed) {
      int numberArg;
      do {
         LOGGER.info("Number of " + element + " [" + minimAllowed + ", " + maximAllowed + "]: ");
         while (!scanner.hasNextInt()) {
            LOGGER.info("Please, enter a valid number: ");
            scanner.next();
         }
         numberArg = scanner.nextInt();
      } while (numberArg < minimAllowed || numberArg > maximAllowed);

      return numberArg;
   }


}
