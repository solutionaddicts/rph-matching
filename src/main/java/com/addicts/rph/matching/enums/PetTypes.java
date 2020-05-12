package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum PetTypes implements UserDefinedEnum {

  Cats("Cats"),
  Dogs("Dogs"),
  Rabbits("Rabbits"),
  Guinea("Guinea"),
  Parrots("Parrots"),
  Snakes("Snakes");

  private final String value;

  PetTypes(String value) {
    this.value = value;
  }

  /**
   * Convert from String to LifeStyle enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static PetTypes getLifestyle(String value) {
    if (value == null) {
      return null;
    }
    for (PetTypes listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<PetTypes> stream() {
    return Stream.of(PetTypes.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
