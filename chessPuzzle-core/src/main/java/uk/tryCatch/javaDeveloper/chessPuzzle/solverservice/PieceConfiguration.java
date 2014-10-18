package uk.tryCatch.javaDeveloper.chessPuzzle.solverservice;

import uk.tryCatch.javaDeveloper.chessPuzzle.exception.InvalidPieceException;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.Piece;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceFactory;
import uk.tryCatch.javaDeveloper.chessPuzzle.piece.PieceType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration of pieces (a certain number of pieces for each piece type) to pass as input program.
 *
 * @author troig
 */
public class PieceConfiguration {

   /** Number of pieces for each piece type */
   private Map<PieceType, Integer> mapNumPieceByType;
   /** Number total of pieces */
   private int totalNumberOfPieces;
   /** Ordered list of all pieces of the configuration */
   private List<Piece> pieceList;

// -- Constructors
//--------------------------------------------------------------------------------------------------

   /**
    * Default constructor for <tt>PieceConfiguration</tt>
    */
   public PieceConfiguration() {
      mapNumPieceByType = new LinkedHashMap<>();
      totalNumberOfPieces = 0;
      pieceList = new ArrayList<>();
   }

// -- Public methods
//--------------------------------------------------------------------------------------------------

   /**
    * Add a number of pieces of a certain type to the <tt>PieceConfiguration</tt>
    *
    * @param pieceType      Piece type
    * @param numberOfPieces Number of pieces of the determined type
    * @throws InvalidPieceException When invalid piece type
    */
   public void addPieces(PieceType pieceType, int numberOfPieces) throws InvalidPieceException {
      if (numberOfPieces > 0) {
         mapNumPieceByType.put(pieceType, numberOfPieces);
         totalNumberOfPieces += numberOfPieces;
         for (int i = 0; i < numberOfPieces; i++) {
            pieceList.add(PieceFactory.createPiece(pieceType));
         }
      }
   }

   /**
    * Returs the ordered list of all pieces of the configuration
    *
    * @return =rdered list of all pieces of the configuration
    */
   public List<Piece> getPieceList() {
      return pieceList;
   }

   /**
    * Returns the number of pieces of a certain type passed by parameter
    *
    * @param pieceType Piece type
    * @return Number of pieces
    */
   public int getNumberOfPieces(PieceType pieceType) {
      return mapNumPieceByType.get(pieceType);
   }

   /**
    * Returns the total number of pieces of the configuration
    *
    * @return Total number of pieces
    */
   public int getTotalNumberOfPieces() {
      return totalNumberOfPieces;
   }
}
