package us.codecraft.webmagic.model.fields;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;

import us.codecraft.webmagic.model.FieldExtractor;
import us.codecraft.webmagic.model.formatter.ObjectFormatter;

public abstract class PageField {
   public abstract boolean operation(Object o, FieldExtractor fieldExtractor, Logger logger) throws IllegalAccessException, InvocationTargetException;

   protected Object convert(String value, ObjectFormatter objectFormatter, Logger logger) {
      try {
         Object format = objectFormatter.format(value);
         logger.debug("String {} is converted to {}", value, format);
         return format;
      } catch (Exception e) {
            logger.error("convert " + value + " to " + objectFormatter.clazz() + " error!", e);
      }
      return null;
   }

   protected void setField(Object o, FieldExtractor fieldExtractor, Object value) throws IllegalAccessException, InvocationTargetException {
      if (value != null) {
         if (fieldExtractor.getSetterMethod() != null)
            fieldExtractor.getSetterMethod().invoke(o, value);
         fieldExtractor.getField().set(o, value);
      }
   }
}
