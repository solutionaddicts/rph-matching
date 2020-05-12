package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum GenderPreference implements UserDefinedEnum {

  Male("Male"),
  Female("Female"),
  Both("Both"),
  Any("Any");

  private final String value;

  GenderPreference(String value) {
    this.value = value;
  }

  /**
   * Convert from String to GenderType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static GenderPreference getGenderType(String value) {
    if (value == null) {
      return null;
    }
    for (GenderPreference listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<GenderPreference> stream() {
    return Stream.of(GenderPreference.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
