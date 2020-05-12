package com.addicts.rph.matching.enums.usertypeshandlers;

import com.addicts.rph.matching.enums.FloorType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter(autoApply = true)
public class FloorTypeConverter implements AttributeConverter<FloorType, String> {

  @Override
  public String convertToDatabaseColumn(FloorType value) {
    if (value == null) {
      return null;
    }

    return value.getValue();
  }

  @Override
  public FloorType convertToEntityAttribute(String value) {
    if (value == null) {
      return null;
    }

    return FloorType.valueOf(value);
  }
}