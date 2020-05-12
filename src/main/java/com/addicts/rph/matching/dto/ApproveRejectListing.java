package com.addicts.rph.matching.dto;

import com.addicts.rph.matching.enums.ListingStatus;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ApproveRejectListing {

  private String rejectReason;

  private String rejectComment;

  private Long listingId;

  private ListingStatus listingStatus;
}
