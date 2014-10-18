package uk.trycatch.javadeveloper.chesspuzzle.board;

/**
 * Specifications of a <tt>Position</tt> (x, y) to access chess board cells.
 *
 * @author troig
 */
public class Position implements Cloneable {

   /** Row number (coordinate X) */
   private final int x;
   /** Column number (coordinate Y) */
   private final int y;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of <tt>Position</tt>
    *
    * @param x Row number (coordinate X)
    * @param y Column number (coordinate Y)
    */
   public Position(final int x, final int y) {
      this.x = x;
      this.y = y;
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns x number of <tt>Position</tt> (coordinate X)
    *
    * @return Row number of <tt>Position</tt> (coordinate X)
    */
   public final int getX() {
      return x;
   }

   /**
    * Returns y number of <tt>Position</tt> (coordinate Y)
    *
    * @return Column number of <tt>Position</tt> (coordinate Y)
    */
   public final int getY() {
      return y;
   }

   @SuppressWarnings("RedundantIfStatement")
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Position)) return false;

      Position position = (Position) o;

      if (y != position.y) return false;
      if (x != position.x) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = x;
      result = 31 * result + y;
      return result;
   }

   @SuppressWarnings({"UnnecessaryLocalVariable", "CloneDoesntCallSuperClone"})
   @Override
   public Object clone() throws CloneNotSupportedException {
      Position copy = new Position(x, y);
      return copy;
   }

   /**
    * String representation of a <tt>Position</tt>.
    * Format: (x, y). Example: <br/>
    * Example: (1,0), (1, 2)
    *
    * @return String representation of a <tt>Position</tt>.
    */
   @Override
   public String toString() {
      return "(" + x + ", " + y + ')';
   }
}
