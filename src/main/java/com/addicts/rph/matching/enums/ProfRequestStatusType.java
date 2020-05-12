package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum ProfRequestStatusType implements UserDefinedEnum {

  New("New"),
  Accepted("Accepted"),
  Rejected("Rejected"),
  OnGoing("On Going"),
  Completed("Completed");
  private final String value;

  ProfRequestStatusType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to ProfRequestStatusType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static ProfRequestStatusType getProfRequestStatusType(String value) {
    if (value == null) {
      return null;
    }
    for (ProfRequestStatusType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<ProfRequestStatusType> stream() {
    return Stream.of(ProfRequestStatusType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
