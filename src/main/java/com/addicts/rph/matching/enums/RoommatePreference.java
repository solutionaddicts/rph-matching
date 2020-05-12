package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum RoommatePreference implements UserDefinedEnum {

  Students("Students"),
  Professionals("Professionals"),
  Both("Both"),
  Any("Any");
  private final String value;

  RoommatePreference(String value) {
    this.value = value;
  }

  /**
   * Convert from String to Roommate enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static RoommatePreference getRoommatePref(String value) {
    if (value == null) {
      return null;
    }
    for (RoommatePreference listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<RoommatePreference> stream() {
    return Stream.of(RoommatePreference.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
