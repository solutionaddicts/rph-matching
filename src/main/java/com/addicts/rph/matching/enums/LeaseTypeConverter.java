package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class LeaseTypeConverter implements AttributeConverter<LeaseType, String> {

  @Override
  public String convertToDatabaseColumn(LeaseType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public LeaseType convertToEntityAttribute(String dbData) {
    return LeaseType.getLease(dbData);
  }
}