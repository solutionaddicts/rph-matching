package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum DietaryPreference implements UserDefinedEnum {

  Vegan("Vegan"),
  Vegetarian("Vegetarian"),
  Both("Both"),
  Any("Any");
  private final String value;

  DietaryPreference(String value) {
    this.value = value;
  }

  /**
   * Convert from String to DietaryPreference enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static DietaryPreference getDietaryPreference(String value) {
    if (value == null) {
      return null;
    }
    for (DietaryPreference listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<DietaryPreference> stream() {
    return Stream.of(DietaryPreference.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
