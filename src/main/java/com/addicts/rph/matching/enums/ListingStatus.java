package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum ListingStatus implements UserDefinedEnum {

  Active("Active"),
  Review("Review"),
  Rejected("Rejected"),
  Archived("Archived"),
  Occupied("Occupied"),
  TemporarilyUnavailable("Temporarily Unavailable"),
  UnavailableUntil("Unavailable Until");

  private final String value;

  ListingStatus(String value) {
    this.value = value;
  }

  /**
   * Convert from String to ListingStatus enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static ListingStatus getStatus(String value) {
    if (value == null) {
      return null;
    }
    for (ListingStatus listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<ListingStatus> stream() {
    return Stream.of(ListingStatus.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
