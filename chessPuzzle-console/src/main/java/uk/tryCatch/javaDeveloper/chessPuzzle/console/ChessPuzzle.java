package uk.tryCatch.javaDeveloper.chessPuzzle.console;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverservice.ChessPuzzleSolution;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverservice.ChessPuzzleSolverService;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverservice.PieceConfiguration;
import uk.tryCatch.javaDeveloper.chessPuzzle.solverservice.impl.BacktrackChessPuzzleSolverService;

import java.util.Scanner;

import static uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType.*;

/**
 * Basic console application to solve the chess puzzle.<br/>
 * Inputs are passed like console arguments and the posible solutions are shown in plain text mode.
 *
 * @author troig
 */
public class ChessPuzzle {

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /** Launch the app */
   @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
   public static void main(String[] args) throws Exception {
      // Inputs passed by command line
      Scanner scanner = new Scanner(System.in);

      System.out.println("\nWelcome to chess puzzle solver.");
      System.out.println("Board dimensions (6x6) and number of pieces (6) to place have been limited for performance reasons\n");

      // Board dimensions
      int numRowsBoard = scanNumber(scanner, "board rows", 2, 6);
      int numColumnsBoard = scanNumber(scanner, "board columns", 2, 6);

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

      // Try to solve the puzzle
      ChessPuzzleSolverService solverService = new BacktrackChessPuzzleSolverService();
      ChessPuzzleSolution solution = solverService.solve(numRowsBoard, numColumnsBoard, pieceConfiguration);

      // Output available solutions
      if (solution.hasError()) {
         System.out.println(solution.getError().getMessage());
      } else {
         System.out.println("\nTotal solutions: " + solution.totalSolutions());
         System.out.println("Time process   : " + solution.getTimeProcess() + " ms\n");

         if (solution.hasSolution()) {
            int numSolution = 1;
            for (ChessBoard chessBoard : solution.getChessBoardSet()) {
               System.out.println("Solution " + numSolution++ + ": ");
               System.out.println(chessBoard);
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
         System.out.print("Number of " + element + " [" + minimAllowed + ", " + maximAllowed + "]: ");
         while (!scanner.hasNextInt()) {
            System.out.print("Please, enter a valid number: ");
            scanner.next();
         }
         numberArg = scanner.nextInt();
      } while (numberArg < minimAllowed || numberArg > maximAllowed);

      return numberArg;
   }
}
