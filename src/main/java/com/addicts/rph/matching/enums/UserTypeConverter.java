package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class UserTypeConverter implements AttributeConverter<UserType, String> {


  @Override
  public String convertToDatabaseColumn(UserType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public UserType convertToEntityAttribute(String dbData) {
    return UserType.getUserType(dbData);
  }
}