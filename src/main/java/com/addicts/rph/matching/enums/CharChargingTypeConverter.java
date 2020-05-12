package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class CharChargingTypeConverter implements AttributeConverter<CarChargingType, String> {

  @Override
  public String convertToDatabaseColumn(CarChargingType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public CarChargingType convertToEntityAttribute(String dbData) {
    return CarChargingType.getCharging(dbData);
  }
}