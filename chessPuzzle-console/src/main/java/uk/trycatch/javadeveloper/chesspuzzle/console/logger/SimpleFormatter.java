package uk.trycatch.javadeveloper.chesspuzzle.console.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Override simpleFormatter to print info without any additional information.
 *
 * @author troig
 */
public class SimpleFormatter extends java.util.logging.SimpleFormatter {

   @Override
   public String format(LogRecord record) {
      if (record.getLevel() == Level.INFO) {
         return record.getMessage();
      } else {
         return super.format(record);
      }
   }
}
