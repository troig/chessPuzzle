package uk.tryCatch.javaDeveloper.chessPuzzle.board;

/**
 * Specifications of a <tt>Position</tt> (row, column) to access chess board cells.
 *
 * @author troig
 */
public class Position implements Cloneable {

   /** Row number (coordinate X) */
   private final int row;
   /** Column number (coordinate Y) */
   private final int column;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Position</tt>
    *
    * @param row    Row number (coordinate X)
    * @param column Column number (coordinate Y)
    */
   public Position(final int row, final int column) {
      this.row = row;
      this.column = column;
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns row number of <tt>Position</tt> (coordinate X)
    *
    * @return Row number of <tt>Position</tt> (coordinate X)
    */
   public final int getRow() {
      return row;
   }

   /**
    * Returns column number of <tt>Position</tt> (coordinate Y)
    *
    * @return Column number of <tt>Position</tt> (coordinate Y)
    */
   public final int getColumn() {
      return column;
   }

   @SuppressWarnings("RedundantIfStatement")
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Position)) return false;

      Position position = (Position) o;

      if (column != position.column) return false;
      if (row != position.row) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = row;
      result = 31 * result + column;
      return result;
   }

   @SuppressWarnings({"UnnecessaryLocalVariable", "CloneDoesntCallSuperClone"})
   @Override
   public Object clone() throws CloneNotSupportedException {
      Position copy = new Position(row, column);
      return copy;
   }

   /**
    * String representation of a <tt>Position</tt>.
    * Format: (row, column). Example: <br/>
    * Example: (1,0), (1, 2)
    *
    * @return String representation of a <tt>Position</tt>.
    */
   @Override
   public String toString() {
      return "(" + row + ", " + column + ')';
   }
}
