package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.BitSet;

/**
 * Abstract definition of a chess <tt>Piece</tt>
 *
 * @author troig
 */
public abstract class Piece implements Cloneable, Comparable<Piece> {

   /** Chess pieces type */
   final protected PieceType pieceType;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constrcutor of chess <tt>Piece</tt>
    *
    * @param pieceType Chess piece type
    */
   protected Piece(PieceType pieceType) {
      this.pieceType = pieceType;
   }

// -- Abstract methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns a <tt>Binary</tt> mask respresentation with all available movements for the piece with the
    * <tt>chessBoard</tt> and source position passed by parameter.<br/>
    * Example for 3x3 board:
    * <pre>
    * (0,2) (1,2) (2,2)
    * (0,1) (1,1) (2,1)
    * (0,0) (1,0) (2,0) -> (0,0) (1,0) (2,0) (0,1) (1,1) (2,1) (0,2) (1,2) (2,2) -> 000000000
    *
    * No movements -> 000000000
    * Piece Queen, sourcePos (0,0): Queen can move to (1,0), (2,0), (0,1), (0,2), (1,1), (2,2) -> 011110101
    *
    * </pre>
    *
    * @param chessBoard Chess board
    * @param sourcePos  Source position
    * @return <tt>Binary</tt> mask respresentation with all available movements for the piece
    */
   public abstract BitSet availableMovementsMask(ChessBoard chessBoard, Position sourcePos);

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Return the piece type
    *
    * @return Piece type
    */
   public final PieceType getPieceType() {
      return pieceType;
   }

   @SuppressWarnings("CloneDoesntCallSuperClone")
   @Override
   public Object clone() throws CloneNotSupportedException {
      try {
         // Clone with reflection
         return getClass().getDeclaredConstructor().newInstance();
      } catch (Exception ex) {
         throw new CloneNotSupportedException();
      }
   }

   @Override
   public int compareTo(@SuppressWarnings("NullableProblems") Piece otherPiece) {
      return pieceType.compareTo(otherPiece.getPieceType());
   }

   // -- Protected methods
//--------------------------------------------------------------------------------------------------

   /**
    * Add a movement to the mask <tt>movements</tt> taking as reference the <tt>sourcePos</tt>, moving
    * <tt>offsetX</tt> positions over X coordinate and <tt>offsetY</tt> positions over Y.
    *
    * @param chessBoard Chess Board
    * @param sourcePos  Source position
    * @param movements  <tt>Binary</tt> mask respresentation with all available movements for the piece
    * @param offsetX    Positions to move over X coordinate
    * @param offsetY    Positions to move over Y coordinate
    */
   protected void addMovement(ChessBoard chessBoard, Position sourcePos, BitSet movements, int offsetX, int offsetY) {
      Position position = new Position(sourcePos.getX() + offsetX, sourcePos.getY() + offsetY);
      if (chessBoard.contains(position)) {
         movements.set(position.getX() + chessBoard.getNumColums() * position.getY());
      }
   }
}
