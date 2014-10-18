package uk.trycatch.javadeveloper.chesspuzzle.board;

import uk.trycatch.javadeveloper.chesspuzzle.piece.Piece;

/**
 * Definition of one <tt>Cell</tt> of a chess board.
 * A <tt>Cell</tt> is determined with his position and can contains a <tt>Piece</tt>.
 *
 * @author troig
 */
public class Cell implements Cloneable {

   /** Position of the <tt>Cell</tt>: (0,1), (2, 1)... */
   private Position position;
   /** Piece placed on the cell (nullable) */
   private Piece piece;
   /** Indicates if the cell is emptty (piece will be unull) */
   private boolean isEmpty;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Cell</tt>
    *
    * @param position Cell position on the chess board
    */
   public Cell(Position position) {
      this.position = position;
      this.piece = null;
      this.isEmpty = true;
   }

   /**
    * Private Cosntructor of <tt>Cell</tt>. Unly used to clone object.
    */
   private Cell() {
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Add the <tt>piece</tt> passed by parameter to the <tt>Cell</tt>
    *
    * @param piece Chess piece
    */
   public void addPiece(Piece piece) {
      this.piece = piece;
      this.isEmpty = false;
   }

   /**
    * Remove the chess <tt>Piece</tt> placed on the <tt>Cell</tt>
    */
   public void removePiece() {
      this.piece = null;
      this.isEmpty = true;
   }

   /**
    * Returns the chess <tt>Piece</tt> placed on the <tt>Cell</tt>
    *
    * @return Chess <tt>Piece</tt> placed on the <tt>Cell</tt>
    */
   public Piece getPiece() {
      return piece;
   }

   /**
    * Returns the <tt>Cell</tt> <tt>position</tt> on the chess board: (1, 0), (1, 1)..
    *
    * @return P<tt>Cell</tt> <tt>position</tt> on the chess board
    */
   public Position getPosition() {
      return position;
   }

   /**
    * Check if the <tt>Cell</tt> is empty
    *
    * @return <tt>true</tt> if the <tt>Cell</tt> is empty,  <tt>false</tt> if cell contains a <tt>Piece</tt>
    */
   public boolean isEmpty() {
      return isEmpty;
   }


   @SuppressWarnings("RedundantIfStatement")
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Cell)) return false;

      Cell cell = (Cell) o;

      if (isEmpty != cell.isEmpty) return false;
      if (piece != null ? !piece.equals(cell.piece) : cell.piece != null) return false;
      if (!position.equals(cell.position)) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = position.hashCode();
      result = 31 * result + (piece != null ? piece.hashCode() : 0);
      result = 31 * result + (isEmpty ? 1 : 0);
      return result;
   }


   @SuppressWarnings("CloneDoesntCallSuperClone")
   @Override
   protected Object clone() throws CloneNotSupportedException {
      Cell copy = new Cell();
      copy.position = (Position) position.clone();
      copy.piece = (piece == null) ? null : (Piece)piece.clone();
      copy.isEmpty = isEmpty;

      return copy;
   }

   /**
    * String representation of a <tt>Cell</tt>.
    * Format: Position: [Empty | Piece short name].<br/>
    * Example: (1,0): Empty, (1, 2): Q
    *
    * @return String representation of a <tt>Cell</tt>.
    */
   @Override
   public String toString() {
      return position.toString() + ": " + (isEmpty ? "Empty" : piece.getPieceType().getShortName());
   }
}
