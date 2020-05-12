package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum LifeStyle implements UserDefinedEnum {

  Single("Single"),
  Shared("Shared"),
  Both("Both");
  private final String value;

  LifeStyle(String value) {
    this.value = value;
  }

  /**
   * Convert from String to LifeStyle enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static LifeStyle getLifestyle(String value) {
    if (value == null) {
      return null;
    }
    for (LifeStyle listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<LifeStyle> stream() {
    return Stream.of(LifeStyle.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
