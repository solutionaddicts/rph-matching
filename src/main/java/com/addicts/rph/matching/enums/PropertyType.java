package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum PropertyType implements UserDefinedEnum {

  Apartments("Apartments"),
  Condo("Condo"),
  TownHome("TownHome"),
  Duplex("Duplex"),
  SingleFamily("SingleFamily");

  private final String value;

  PropertyType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to PropertyType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static PropertyType getPropertyType(String value) {
    if (value == null) {
      return null;
    }
    for (PropertyType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<PropertyType> stream() {
    return Stream.of(PropertyType.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
