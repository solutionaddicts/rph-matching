package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class DietaryPrefConverter implements AttributeConverter<DietaryPreference, String> {


  @Override
  public String convertToDatabaseColumn(DietaryPreference attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public DietaryPreference convertToEntityAttribute(String dbData) {
    return DietaryPreference.getDietaryPreference(dbData);
  }
}