package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum FloorType implements UserDefinedEnum {

  Wood("Wood"),
  Carpet("Carpet"),
  Tile("Tile"),
  Marble("Marble"),
  Granite("Granite"),
  PVC("PVC"),
  None("None");

  private final String value;

  FloorType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to FloorType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static FloorType getFloorType(String value) {
    for (FloorType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<FloorType> stream() {
    return Stream.of(FloorType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
