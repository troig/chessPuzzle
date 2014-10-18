package uk.tryCatch.javaDeveloper.chessPuzzle.piece;

/**
 * Enumeration that defines the different valid types of chess <tt>Piece</tt>
 *
 * @author troig
 */
public enum PieceType {
   QUEEN("Q", "Queen", Queen.class),
   ROCK("R", "Rock", Rock.class),
   BISHOP("B", "Bishop", Bishop.class),
   KNIGHT("L", "Knight", Knight.class),
   KING("K", "King", King.class);

   /** Short name representation of the piece. Example: Q; Queen */
   private final String shortName;
   /** Name of the piece, Example; Queen */
   private final String name;
   /** Class witch define the piece */
   private final Class<? extends Piece> clazz;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor of enum <tt>PieceType</tt>
    *
    * @param shortName Short name representation of the piece. Example: Q; Queen
    * @param name      Name of the piece, Example; Queen
    * @param clazz     Class witch define the piece
    */
   private PieceType(String shortName, String name, Class<? extends Piece> clazz) {
      this.shortName = shortName;
      this.name = name;
      this.clazz = clazz;
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Returns the short name representation of the piece. Example: Q; Queen
    *
    * @return Short name representation of the piece. Example: Q; Queen
    */
   public String getShortName() {
      return shortName;
   }

   /**
    * Returns the name of the piece, Example; Queen
    *
    * @return Name of the piece, Example; Queen
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the class witch define the piece
    *
    * @return Class witch define the piece
    */
   public Class<? extends Piece> getClazz() {
      return clazz;
   }
}
