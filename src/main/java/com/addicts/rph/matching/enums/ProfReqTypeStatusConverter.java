package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class ProfReqTypeStatusConverter implements
    AttributeConverter<ProfRequestStatusType, String> {


  @Override
  public String convertToDatabaseColumn(ProfRequestStatusType attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public ProfRequestStatusType convertToEntityAttribute(String dbData) {
    return ProfRequestStatusType.getProfRequestStatusType(dbData);
  }
}