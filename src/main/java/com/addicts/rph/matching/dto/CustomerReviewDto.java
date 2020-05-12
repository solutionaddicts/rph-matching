package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class CustomerReviewDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String reviewedBy;

  private String reviewedAt;

  private Integer rating;

  private String additionalComments;

  private String serviceReceived;
}
