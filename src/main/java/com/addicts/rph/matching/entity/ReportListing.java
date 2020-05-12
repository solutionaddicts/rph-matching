package com.addicts.rph.matching.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "rph_report_listing")
public class ReportListing implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Integer reportId;

  @Column(name = "listing_id", nullable = false)
  private Integer listingId;

  @Column(name = "subject", nullable = false)
  private String subject;

  @Column(name = "comments", nullable = false)
  private String comments;

  @Column(name = "reported_by", nullable = false)
  private Integer reportedBy;

  @Column(name = "reported_at", nullable = false)
  private ZonedDateTime reportedAt;
}
