package com.addicts.rph.matching.dto;

import com.addicts.rph.matching.enums.LeaseType;
import com.addicts.rph.matching.enums.ListingStatus;
import com.addicts.rph.matching.enums.PropertyType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class RentalPropertiesListRequestDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Set<PropertyType> propertyTypes;
  private String zipCode;
  private String city;
  private String state;
  private LeaseType leasingType;
  private Integer minDimensions;
  private Integer maxDimensions;
  private Boolean arePetsAllowed;
  private Set<String> petsTypesAllowed;
  private Boolean laundryAmenitiesFree;
  private Boolean creditReport;
  private String country;
  private String bedRooms;
  private Integer baths;
  private Integer minLease;
  private Integer maxLease;
  private List<String> lifeStyles;
  private Integer rentMinPrice;
  private Integer rentMaxPrice;
  private ZonedDateTime updatedDateTime;
  private Integer userId;
  private Boolean onStreet;
  private Boolean privateLot;
  private Boolean driveWay;
  private Boolean garage;
  private Boolean furnished;
  private Boolean disabilityAccess;
  private Boolean dishwasher;
  private Double noBrokerFree;
  private Boolean electricCarCharge;
  private Boolean hvac;
  private Boolean fitnessCenter;
  private Boolean utilitiesIncluded;
  private Boolean vegan;
  private Boolean vegetarian;
  private Boolean elevator;
  private Boolean smokeFriendly;
  private Boolean lgbtqFriendly;
  private Boolean fireplace;
  private Boolean patio;
  private Boolean hasPhotos;
  private Boolean parkingAvailability;
  private ListingStatus status;

}
