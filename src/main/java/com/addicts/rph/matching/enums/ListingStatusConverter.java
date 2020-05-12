package com.addicts.rph.matching.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author sravantatikonda
 */
@Converter
public class ListingStatusConverter implements AttributeConverter<ListingStatus, String> {


  @Override
  public String convertToDatabaseColumn(ListingStatus attribute) {
    if (attribute != null) {
      return attribute.getValue();
    }
    return null;
  }

  @Override
  public ListingStatus convertToEntityAttribute(String dbData) {
    return ListingStatus.getStatus(dbData);
  }
}