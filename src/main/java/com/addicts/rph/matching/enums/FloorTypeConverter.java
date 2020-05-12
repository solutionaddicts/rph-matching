package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class FloorTypeConverter implements AttributeConverter<FloorType, String> {


  @Override
  public String convertToDatabaseColumn(FloorType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public FloorType convertToEntityAttribute(String dbData) {
    return FloorType.getFloorType(dbData);
  }
}