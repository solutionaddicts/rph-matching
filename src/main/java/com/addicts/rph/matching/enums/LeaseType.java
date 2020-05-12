package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum LeaseType implements UserDefinedEnum {

  New("New"),
  Sublet("Sublet");

  private final String value;

  LeaseType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to LeaseType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static LeaseType getLease(String value) {
    if (value == null) {
      return null;
    }
    for (LeaseType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<LeaseType> stream() {
    return Stream.of(LeaseType.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
