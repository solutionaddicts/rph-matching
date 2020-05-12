package com.addicts.rph.matching.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class RenterProfileDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer rentalProfId;

  private Integer userId;

  /**
   * No. of tenants moving in
   */
  private Integer numTenants;

  private ZonedDateTime tentativeMoveInDate;

  private Boolean smokeFriendly;

  private String creditHistoryRange;

  private Boolean arePetsAllowed;

  private StringList pets;

  private Boolean singleRoom;

  private Integer numRoommatesComfort;

  private Integer workZipCode;

  private String companyName;

  private String incomeRange;
}
