package uk.tryCatch.javaDeveloper.chessPuzzle.board;


import uk.tryCatch.javaDeveloper.chessPuzzle.exception.ChessException;
import uk.tryCatch.javaDeveloper.chessPuzzle.exception.InvalidPieceException;
import uk.tryCatch.javaDeveloper.chessPuzzle.exception.InvalidPositionException;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.Piece;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceFactory;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType;

import java.util.*;

/**
 * Definition of the chess board. We understand a <tt>ChessBoard</tt> like a bi dimensional matrix of <tt>Cell</tt> with
 * variable dimension N x M (numRows x numColumns)
 *
 * @author troig
 */
public class ChessBoard implements Cloneable {

   /** Number of rows of the chess board */
   private int numRows;
   /** Number of columns of the chess board */
   private int numColums;
   /** Cell matrix which defines the chess board */
   private Cell[][] gridBoard;
   /** List of all positions of the chess borad */
   private List<Position> positionList;
   /** Set that contains all positions of the chess board occupied (containing a piece) */
   private Set<Position> positionOccupiedSet;
   /** Cache with a binary mask representation of all available movement for each piece for each position */
   private Map<Position, Map<PieceType, BitSet>> movementsCache = new HashMap<>();

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default initialization of a <tt>ChessBoard</tt> with dimension (<tt>numRows</tt>x<tt>numColumns</tt>)
    *
    * @param numRows   Number of rows of the chess board
    * @param numColums Number of columns of the chess board
    */
   public ChessBoard(int numRows, int numColums) throws ChessException {
      this.numRows = numRows;
      this.numColums = numColums;

      // Grid of chessBoard initalization
      this.gridBoard = new Cell[numRows][numColums];
      this.positionList = new ArrayList<>();
      this.positionOccupiedSet = new HashSet<>();
      for (int row = 0; row < numRows; row++) {
         for (int column = 0; column < numColums; column++) {
            Position position = new Position(column, row);
            positionList.add(position);
            this.gridBoard[row][column] = new Cell(position);
         }
      }

      // Pre-Cache a binary mask of all available movements for each piece for each position on the ChessBoard
      loadMovementsCache();
   }

   /** Private Constructor only for clone purpose */
   private ChessBoard() {
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns the number of rows of the <tt>ChessBoard</tt>
    *
    * @return number of rows of the <tt>ChessBoard</tt>
    */
   public int getNumRows() {
      return numRows;
   }

   /**
    * Returns the number of columns of the <tt>ChessBoard</tt>
    *
    * @return number of columns of the <tt>ChessBoard</tt>
    */
   public int getNumColums() {
      return numColums;
   }

   /**
    * Returns the <tt>Cell</tt> corresponding to the <tt>Position</tt> passed by parameter.
    *
    * @return <tt>Cell</tt> corresponding to the <tt>Position</tt> passed by parameter.
    */
   public final Cell getCell(Position position) {
      return gridBoard[position.getY()][position.getX()];
   }

   /**
    * Add the chess <tt>piece</tt> passed by parameter to the <tt>Cell</tt> corresponding to the indicated
    * <tt>position</tt>
    *
    * @param position Position on the chess board
    * @param piece    Chess piece to place
    * @throws InvalidPositionException When invalid position
    */
   public void addPiece(Position position, Piece piece) throws InvalidPositionException {
      try {
         // Add piece to the cell
         getCell(position).addPiece(piece);
         // Mark position as occupied
         positionOccupiedSet.add(position);
      } catch (Exception ex) {
         throw new InvalidPositionException();
      }
   }

   /**
    * Remove the chess <tt<Piece</tt> from the <tt>Cell</tt> with the <tt>position</tt> passed by parameter.
    *
    * @param position Position on the chess board
    * @throws InvalidPositionException When invalid position
    */
   public void removePiece(Position position) throws InvalidPositionException {
      try {
         // Remove the pice from the cell
         getCell(position).removePiece();
         // Unmark position as occupied
         positionOccupiedSet.remove(position);
      } catch (Exception ex) {
         throw new InvalidPositionException();
      }
   }

   /**
    * Check if the <tt>position</tt> passed by parametr is empty (any piece place in)
    *
    * @param position Position on the chess board
    * @return <tt>true</tt> if the <tt>position</tt> passed by parameter is empty (any piece place in)
    * @throws InvalidPositionException When invalid position
    */
   public boolean isEmpty(Position position) throws InvalidPositionException {
      try {
         return getCell(position).isEmpty();
      } catch (Exception ex) {
         throw new InvalidPositionException();
      }
   }

   /**
    * Check if the <tt>position</tt> passed by parameter is valid (is contained in the chess board)
    *
    * @param position Position on the chess board
    * @return <tt>true</tt> if the <tt>position</tt> passed by parameter is valid
    */
   public boolean contains(Position position) {
      return positionList.contains(position);
   }

   /**
    * Remove all chess pieces placed on the <tt>ChessBoard</tt>
    */
   public void removeAllPieces() {
      // Clear set occupied position
      positionOccupiedSet.clear();
      // Remove all pieces of all occupied cells
      for (Cell[] cellArray : gridBoard) {
         for (Cell cell : cellArray) {
            if (!cell.isEmpty()) cell.removePiece();
         }
      }
   }

   /**
    * Returns a list with all positions of the chess borad.
    *
    * @return List with all positions of the chess borad.
    */
   public final List<Position> getAllPosition() {
      return positionList;
   }

   /**
    * Check if the <tt>piece</tt> on the <tt>position</tt> passed by parameter can be placed 'Non attack' on the board.
    * This means:
    * <ul>
    * <li>Piece is not attacked for other pieces already placed on the board</li>
    * <li>Piece no attacks other pieces already placed on the board</li>
    * </ul>
    *
    * @param piece    Chess piece
    * @param position Position on the chess board
    * @return <tt>true</tt> if <tt>position</tt> can be attacked for any piece placed on the chess board.
    * @throws InvalidPositionException
    */
   public boolean canBePlacedNonAttack(Piece piece, Position position) throws InvalidPositionException {
      if (positionOccupiedSet.isEmpty()) return true;

      try {
         // Check piece is not attacked
         for (Position positionOccupied : positionOccupiedSet) {
            Piece otherPiece = getCell(positionOccupied).getPiece();
            int positionMask = position.getX() + getNumColums() * position.getY();

            // Test if the bit of the position 'posMask' is marked.
            if (movementsCache.get(positionOccupied).get(otherPiece.getPieceType()).get(positionMask)) {
               return false;
            }
         }

         // Check that piece no attack other pieces
         for (Position positionOccupied : positionOccupiedSet) {
            int positionOccupiedMask = positionOccupied.getX() + getNumColums() * positionOccupied.getY();
            if (movementsCache.get(position).get(piece.getPieceType()).get(positionOccupiedMask)) {
               return false;
            }
         }
      } catch (Exception ex) {
         throw new InvalidPositionException();
      }
      return true;
   }

   @SuppressWarnings("RedundantIfStatement")
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ChessBoard)) return false;

      ChessBoard chessBoard = (ChessBoard) o;

      // Use a chessBoard key to comparision, for performance reasons
      if (!createChessBoardKey(chessBoard).equals(createChessBoardKey(this))) return false;
      return true;
   }

   @Override
   public int hashCode() {
      // Use a chessBoard key to hashCoding, for performance reasons
      return createChessBoardKey(this).hashCode();
   }

   @SuppressWarnings("CloneDoesntCallSuperClone")
   @Override
   public Object clone() throws CloneNotSupportedException {
      ChessBoard copy = new ChessBoard();
      copy.numRows = numRows;
      copy.numColums = numColums;

      // Copy gridBoard
      copy.gridBoard = this.gridBoardCopyOf();

      // Copy positionList
      List<Position> positionListCopy = new ArrayList<>();
      for (Position position : this.positionList) {
         positionListCopy.add((Position) position.clone());
      }
      copy.positionList = positionListCopy;

      // Copy positionOccupiedSet
      Set<Position> positionOccupiedSetCopy = new HashSet<>();
      for (Position position : this.positionOccupiedSet) {
         positionOccupiedSetCopy.add((Position) position.clone());
      }
      copy.positionOccupiedSet = positionOccupiedSetCopy;

      // Copy movements cache
      Map<Position, Map<PieceType, BitSet>> movementsCacheCopy = new Hashtable<>();
      for (Position position : movementsCache.keySet()) {
         Map<PieceType, BitSet> movementsByPieceCopy = new Hashtable<>();
         Map<PieceType, BitSet> movementsByPiece = movementsCache.get(position);
         for (PieceType pieceType : movementsByPiece.keySet()) {
            movementsByPieceCopy.put(pieceType, (BitSet) movementsByPiece.get(pieceType).clone());
         }
         movementsCacheCopy.put((Position) position.clone(), movementsByPieceCopy);
      }
      copy.movementsCache = movementsCacheCopy;

      return copy;
   }

   /**
    * Returns a slim copy (only numRows, numColumns and gridBoard) of the <tt>ChessBoard</tt>.
    *
    * @return slim copy (only numRows, numColumns and gridBoard)
    * @throws CloneNotSupportedException Clone Cell error
    */
   public ChessBoard slimCopyOf() throws CloneNotSupportedException {
      ChessBoard copy = new ChessBoard();
      copy.numRows = numRows;
      copy.numColums = numColums;

      copy.gridBoard = this.gridBoardCopyOf();
      return copy;
   }

   /**
    * String representation of the chess board.<br/>
    * <pre>
    * Example:
    * * Q * * *
    * * * * Q *
    * * * Q * *
    * Q * * * *
    * </pre>
    *
    * @return String respresentation of the chess board
    */
   @Override
   public String toString() {
      StringBuilder chessBoardString = new StringBuilder();
      for (int i = gridBoard.length - 1; i >= 0; i--) {
         Cell[] cellArray = gridBoard[i];
         for (Cell cell : cellArray) {
            Piece piece = cell.getPiece();
            chessBoardString.append((piece == null) ? "*" : piece.getPieceType().getShortName());
            chessBoardString.append(" ");
         }
         chessBoardString.append("\n");
      }
      return chessBoardString.toString();
   }

   /**
    * Generates a unique key for the representation of the <tt>ChessBoard</tt>.<br/>
    * Format: ****Q**B***
    *
    * @return <tt>ChessBoard</tt> key
    */
   public String createChessBoardKey(ChessBoard chessBoard) {
      StringBuilder chessBoardKey = new StringBuilder();
      for (int i = chessBoard.gridBoard.length - 1; i >= 0; i--) {
         Cell[] cellArray = chessBoard.gridBoard[i];
         for (Cell cell : cellArray) {
            Piece piece = cell.getPiece();
            chessBoardKey.append((piece == null) ? "*" : piece.getPieceType().getShortName());
         }
      }
      return chessBoardKey.toString();
   }

// -- Private methods
//--------------------------------------------------------------------------------------------------

   /**
    * Load the local cache <tt>movementsCache</tt> with a binary mask representation of all available movement for
    * each piece for each position.
    *
    * @throws InvalidPieceException Error creating piece
    */
   private void loadMovementsCache() throws InvalidPieceException {
      // Init a List with an instance of each piece
      List<Piece> pieceList = new ArrayList<>();
      for (PieceType pieceType : PieceType.values()) {
         pieceList.add(PieceFactory.createPiece(pieceType));
      }
      for (Position position : positionList) {
         Map<PieceType, BitSet> movementsByPiece = new HashMap<>();
         for (Piece piece : pieceList) {
            movementsByPiece.put(piece.getPieceType(), piece.availableMovementsMask(this, position));
         }
         movementsCache.put(position, movementsByPiece);
      }
   }

   /**
    * Returns a deep copy of <tt>gridBoard</tt>
    *
    * @return Deep copy of <tt></tt>
    * @throws CloneNotSupportedException Cell clone error
    */
   private Cell[][] gridBoardCopyOf() throws CloneNotSupportedException {
      Cell[][] gridBoardCopy = new Cell[numRows][numColums];
      for (int row = 0; row < numRows; row++) {
         for (int column = 0; column < numColums; column++) {
            gridBoardCopy[row][column] = (Cell) this.gridBoard[row][column].clone();
         }
      }
      return gridBoardCopy;
   }
}
