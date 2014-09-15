package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

import uk.tryCatch.javaDeveloper.chessPuzzle.board.ChessBoard;
import uk.tryCatch.javaDeveloper.chessPuzzle.board.Position;

import java.util.Set;

/**
 * Abstract definition of a chess <tt>Piece</tt>
 *
 * @author troig
 */
public abstract class Piece implements Cloneable {

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
    * Return a set with all available movements for the piece with the <tt>chessBoard</tt> and source position passed
    * by parameter.
    *
    * @param chessBoard Chess board
    * @param sourcePos  Source position
    * @return All available movements for the piece
    */
   public abstract Set<Position> availableMovements(ChessBoard chessBoard, Position sourcePos);

   /**
    * Check if the piece on the <tt>chessBoard</tt> placed on position <tt>source</tt> can attack the position
    * <tt>target</tt>
    *
    * @param chessBoard Chess board
    * @param source     Source position (where piece is placed)
    * @param target     Target position (check if piece can attack it)
    * @return <tt>true</tt> if the piece on position <tt>source</tt> can attack the position <tt>target></tt>
    */
   public abstract boolean canAttack(ChessBoard chessBoard, Position source, Position target);

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Return the piece type
    *
    * @return Piece type
    */
   public PieceType getPieceType() {
      return pieceType;
   }

   @Override
   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}
