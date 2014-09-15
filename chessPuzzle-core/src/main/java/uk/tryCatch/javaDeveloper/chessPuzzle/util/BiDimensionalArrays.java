package uk.tryCatch.javaDeveloper.chessPuzzle.util;

import java.lang.reflect.Array;

/**
 * Utilities for BiDimensional arrays management
 *
 * @author troig
 */
public class BiDimensionalArrays {

   /**
    * Returns a deep copy of the bidimensional array <tt>source</tt> pased by parameter
    *
    * @param source BiDimensional array to copy
    * @param <T>    Generic element
    * @return Deep copy of <tt>source</tt>
    */
   @SuppressWarnings("unchecked")
   public static <T> T[][] deepCopyOf(T[][] source) {
      Class<? extends T[][]> type = (Class<? extends T[][]>) source.getClass();
      T[][] copy = (T[][]) Array.newInstance(type.getComponentType(), source.length);

      Class<? extends T[]> itemType = (Class<? extends T[]>) source[0].getClass();
      for (int i = 0; i < source.length; i++) {
         copy[i] = (T[]) Array.newInstance(itemType.getComponentType(), source[i].length);
         System.arraycopy(source[i].clone(), 0, copy[i], 0, source[i].length);
      }
      return copy;
   }

}
