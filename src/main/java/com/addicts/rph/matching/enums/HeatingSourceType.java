package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum HeatingSourceType implements UserDefinedEnum {

  HeatingOil("Heating Oil"),
  Propane("Propane"),
  NaturalGas("Natural Gas"),
  Electricity("Electricity");

  private final String value;

  HeatingSourceType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to HeatingSourceType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static HeatingSourceType getHeatingSourceType(String value) {
    if (value == null) {
      return null;
    }
    for (HeatingSourceType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<HeatingSourceType> stream() {
    return Stream.of(HeatingSourceType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
