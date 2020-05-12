package com.addicts.rph.matching.dto;

import com.addicts.rph.matching.enums.ProfRequestStatusType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ProfessionalRequestDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer requestId;

  private Integer requestedById;

  private ZonedDateTime requestedOn;

  private String reqSpeciality;

  private String notes;

  private Address requestLocation;

  private Integer professionalUserId;

  private String fullName;

  private ZonedDateTime requiredOn;

  /**
   * A request status can be in New, Accepted, Rejected, OnGoing, Completed
   */
  private ProfRequestStatusType requestStatus;
}
