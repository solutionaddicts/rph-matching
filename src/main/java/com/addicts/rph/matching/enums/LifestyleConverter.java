package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class LifestyleConverter implements AttributeConverter<LifeStyle, String> {


  @Override
  public String convertToDatabaseColumn(LifeStyle attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public LifeStyle convertToEntityAttribute(String dbData) {
    return LifeStyle.getLifestyle(dbData);
  }
}