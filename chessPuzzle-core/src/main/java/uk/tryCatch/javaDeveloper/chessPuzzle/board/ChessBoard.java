package uk.tryCatch.javaDeveloper.chessPuzzle.board;


import uk.tryCatch.javaDeveloper.chessPuzzle.exception.InvalidPositionException;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.Piece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default initialization of a <tt>ChessBoard</tt> with dimension (<tt>numRows</tt>x<tt>numColumns</tt>)
    *
    * @param numRows   Number of rows of the chess board
    * @param numColums Number of columns of the chess board
    */
   public ChessBoard(int numRows, int numColums) {
      this.numRows = numRows;
      this.numColums = numColums;

      // Grid of chessBoard initalization
      this.gridBoard = new Cell[numRows][numColums];
      this.positionList = new ArrayList<>();
      this.positionOccupiedSet = new HashSet<>();
      for (int row = 0; row < numRows; row++) {
         for (int column = 0; column < numColums; column++) {
            Position position = new Position(row, column);
            positionList.add(position);
            this.gridBoard[row][column] = new Cell(position);
         }
      }
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
   public Cell getCell(Position position) {
      return gridBoard[position.getRow()][position.getColumn()];
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
   public List<Position> getAllPosition() {
      return positionList;
   }

   /**
    * Check if the <tt>position</tt> passed by parameter can be attacked for any piece placed on the chess board.
    *
    * @param position Position on the chess board
    * @return <tt>true</tt> if <tt>position</tt> can be attacked for any piece placed on the chess board.
    */
   public boolean canBeAttacked(Position position) {
      for (Position positionOccupied : positionOccupiedSet) {
         Piece piece = getCell(positionOccupied).getPiece();
         // TODO (troig 15/09/14) Change and call to piece.canAttack(this, positionOccupied, position)
         Set<Position> setPosibleMovements = piece.availableMovements(this, positionOccupied);
         if (setPosibleMovements.contains(position)) return true;
      }
      return false;
   }

   @SuppressWarnings("RedundantIfStatement")
   @Override
   public boolean equals(Object o) {
      // TODO (troig 14/09/14) CHANGE. PROVISIONAL ONLY FOR TEST PURPOSE
      return toString().equals(o.toString());
   }

   @Override
   public int hashCode() {
      // TODO (troig 14/09/14) CHANGE. PROVISIONAL ONLY FOR TEST PURPOSE
      return toString().hashCode();
   }

   @SuppressWarnings("CloneDoesntCallSuperClone")
   @Override
   public Object clone() throws CloneNotSupportedException {
      // TODO (troig 14/09/14) CHANGE. PROVISIONAL ONLY FOR TEST PURPOSE
      ChessBoard copy = new ChessBoard(this.numRows, this.numColums);
      Cell[][] gridBoardCopy = new Cell[numRows][numColums];
      for (int row = 0; row < numRows; row++) {
         for (int column = 0; column < numColums; column++) {
            gridBoardCopy[row][column] = (Cell) this.gridBoard[row][column].clone();
         }
      }
      copy.gridBoard = gridBoardCopy;

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

      return copy;
   }

   /**
    * String represetnation of the chess board.<br/>
    *
    * Example:<p/></p>
    * * Q * * * <br/>
    * * * * Q * <br/>
    * * * Q * * <br/>
    * Q * * * * <br/>
    *
    * @return String respresentation of the chess board
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      for (Cell[] cellArray : gridBoard) {
         for (Cell cell : cellArray) {
            Piece piece = cell.getPiece();
            builder.append((piece == null) ? "*" : piece.getPieceType().getShortName());
            builder.append(" ");
         }
         builder.append("\n");
      }
      builder.append("\n");
      return builder.toString();
   }
}
