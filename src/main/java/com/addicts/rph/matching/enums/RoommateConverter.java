package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class RoommateConverter implements AttributeConverter<RoommatePreference, String> {


  @Override
  public String convertToDatabaseColumn(RoommatePreference attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public RoommatePreference convertToEntityAttribute(String dbData) {
    return RoommatePreference.getRoommatePref(dbData);
  }
}