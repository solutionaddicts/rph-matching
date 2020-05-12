package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different Listing Statuses
 *
 * @author sravantatikonda
 */
public enum Canada implements UserDefinedEnum {

  Alberta("Alberta"),
  BritishColumbia("British Columbia"),
  Manitoba("Manitoba"),
  NewBrunswick("New Brunswick"),
  NewFoundlandandLabrador("Newfoundland and Labrador"),
  NovaScotia("Nova Scotia"),
  Ontario("Ontario"),
  PrinceEdwardIsland("Prince Edward Island"),
  Québec("Québec"),
  Saskatchewan("Saskatchewan"),
  //Territories
  NorthwestTerritories("Northwest Territories"),
  Nunavut("Nunavut"),
  Yukon("Yukon");
  private final String value;

  Canada(String value) {
    this.value = value;
  }

  /**
   * Convert from String to Canada enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static Canada getCanada(String value) {
    if (value == null) {
      return null;
    }
    for (Canada listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<Canada> stream() {
    return Stream.of(Canada.values());
  }

  @Override
  public String getValue() {
    return value;
  }
}
