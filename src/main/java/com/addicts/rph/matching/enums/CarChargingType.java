package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum CarChargingType implements UserDefinedEnum {

  Paid("Paid"),
  Free("Free");

  private final String value;

  CarChargingType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to CarChargingType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static CarChargingType getCharging(String value) {
    if (value == null) {
      return null;
    }
    for (CarChargingType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<CarChargingType> stream() {
    return Stream.of(CarChargingType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
