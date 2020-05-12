package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ListingResponseDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer responseId;

  private Integer respBy;

  private Integer respTo;

  private String respByEmail;

  private String respByPhone;

  private String resOn;

  private Long listingId;

  private String message;
}
