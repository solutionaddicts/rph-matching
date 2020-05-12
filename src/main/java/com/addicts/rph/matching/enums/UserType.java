package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum UserType implements UserDefinedEnum {

  Admin("Admin"),
  CSR("CSR"),
  Regular("Regular"),
  Agent("Agent"),
  Broker("Broker"),
  Photographer("Photographer"),
  Plumber("Plumber"),
  Electrician("Electrician"),
  HVAC("HVAC"),
  HomeRemodeling("Home Remodeling");

  private final String value;

  UserType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to UserType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static UserType getUserType(String value) {
    if (value == null) {
      return null;
    }
    for (UserType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<UserType> stream() {
    return Stream.of(UserType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
