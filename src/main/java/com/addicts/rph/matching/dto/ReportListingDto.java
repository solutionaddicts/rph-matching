package com.addicts.rph.matching.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ReportListingDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer reportId;

  private Long listingId;

  private String subject;

  private String comments;

  private Integer reportedBy;

  private ZonedDateTime reportedAt;
}
