package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class GenderPreferenceConverter implements AttributeConverter<GenderPreference, String> {


  @Override
  public String convertToDatabaseColumn(GenderPreference attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public GenderPreference convertToEntityAttribute(String dbData) {
    return GenderPreference.getGenderType(dbData);
  }
}