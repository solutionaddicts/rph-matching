package com.addicts.rph.matching.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;


/**
 * @author sravantatikonda
 */
@Data
public class Review implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer reviewedBy;

  private Integer reviewedTo;

  private String workType;

  private Double stars;

  private String description;

  private ZonedDateTime createdDate;

  private ZonedDateTime updatedDate;
}
