package com.addicts.rph.matching.enums;

import java.util.stream.Stream;

/**
 * Enumeration defining different States in USA
 *
 * @author sravantatikonda
 */
public enum USA implements UserDefinedEnum {

  Alabama("Alabama"),
  Montana("Montana"),
  Alaska("Alaska"),
  Nebraska("Nebraska"),
  Arizona("Arizona"),
  Nevada("Nevada"),
  Arkansas("Arkansas"),
  NewHampshire("New Hampshire"),
  California("California"),
  NewJersey("New Jersey"),
  Colorado("Colorado"),
  NewMexico("New Mexico"),
  Connecticut("Connecticut"),
  NewYork("NewYork"),
  Delaware("Delaware"),
  NorthCarolina("North Carolina"),
  Florida("Florida"),
  NorthDakota("North Dakota"),
  Georgia("Georgia"),
  Ohio("Ohio"),
  Hawaii("Hawaii"),
  Oklahoma("Oklahoma"),
  Idaho("Idaho"),
  Oregon("Oregon"),
  Illinois("Illinois"),
  Pennsylvania("Pennsylvania"),
  Indiana("Indiana"),
  RhodeIsland("Rhode Island"),
  Iowa("Iowa"),
  SouthCarolina("South Carolina"),
  Kansas("Kansas"),
  SouthDakota("South Dakota"),
  Kentucky("Kentucky"),
  Tennessee("Tennessee"),
  Louisiana("Louisiana"),
  Texas("Texas"),
  Maine("Maine"),
  Utah("Utah"),
  Maryland("Maryland"),
  Vermont("Vermont"),
  Massachusetts("Massachusetts"),
  Virginia("Virginia"),
  Michigan("Michigan"),
  Washington("Washington"),
  Minnesota("Minnesota"),
  WestVirginia("West Virginia"),
  Mississippi("Mississippi"),
  Wisconsin("Wisconsin"),
  Missouri("Missouri"),
  Wyoming("Wyoming"),
  WashingtonDC("Washington DC");
  private final String value;

  USA(String value) {
    this.value = value;
  }

  /**
   * Convert from String to USA enumeration
   *
   * @param value the String to convert from
   * @return the enumeration, or null if value was null
   */
  public static USA getUsa(String value) {
    if (value == null) {
      return null;
    }
    for (USA listingStatus : values()) {
      if (listingStatus.value.equalsIgnoreCase(value)) {
        return listingStatus;
      }
    }
    throw new IllegalArgumentException("Unknown type to map from: " + value);
  }

  public static Stream<USA> stream() {
    return Stream.of(USA.values());
  }

  @Override
  public String getValue() {
    return value;
  }

}
