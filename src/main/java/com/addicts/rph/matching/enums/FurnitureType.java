package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum FurnitureType implements UserDefinedEnum {

  Sofa("Sofa"),
  Bed("Bed"),
  Chair("Chair"),
  Table("Table"),
  BeanBag("Bean Bag"),
  Wardrobe("Wardrobe");

  private final String value;

  FurnitureType(String value) {
    this.value = value;
  }

  /**
   * Convert from String to FurnitureType enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static FurnitureType getFurnitureType(String value) {
    if (value == null) {
      return null;
    }
    for (FurnitureType listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<FurnitureType> stream() {
    return Stream.of(FurnitureType.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
