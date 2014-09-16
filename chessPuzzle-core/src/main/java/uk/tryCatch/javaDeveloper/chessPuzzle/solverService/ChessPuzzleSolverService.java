package uk.tryCatch.javaDeveloper.chessPuzzle.solverService;

import org.springframework.stereotype.Service;
import uk.tryCatch.javaDeveloper.chessPuzzle.exception.ChessException;

/**
 * Service for puzzle solver.<br/>
 * The service has to been able to find all unique configurations of a set of normal chess pieces on  a chess board with
 * dimensions M×N where none of the pieces is in a position to take any of the others. As imput, the service receives:
 * <ul>
 * <li>The dimensions of the board: M, N.</li>
 * <li>The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the board.</li>
 * </ul>
 *
 * @author troig
 */
@Service("chessPuzzleSolver")
public interface ChessPuzzleSolverService {

   /**
    * Find all unique configurations of a set of normal chess pieces (Queen, Bishop, Rock, Knight and King) on a chess
    * board with dimensions <tt>numRows</tt> x <tt>numColumns </tt> where none of the pieces is in a position to take
    * any of the others.
    *
    * @param numRowsBoard       Number of rows of the chess board
    * @param numColumsBoard     Number of columns of the chess board
    * @param pieceConfiguration Piece configuration for the puzzle (X pieces each certain piece type)
    * @return Solution for the puzzle (N chessBoard solved, timeProces...)
    * @throws ChessException Solver errros
    */
   public ChessPuzzleSolution solve(int numRowsBoard, int numColumsBoard,
                                    PieceConfiguration pieceConfiguration) throws ChessException;
}
