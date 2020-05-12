package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class PropertyTypeConverter implements AttributeConverter<PropertyType, String> {


  @Override
  public String convertToDatabaseColumn(PropertyType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public PropertyType convertToEntityAttribute(String dbData) {
    return PropertyType.getPropertyType(dbData);
  }
}