package com.addicts.rph.matching.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "rph_lstng_resp")
@EntityListeners(AuditingEntityListener.class)
public class ListingResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "ID", nullable = false)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer responseId;

  @Column(name = "RESP_TO", nullable = false)
  private Integer respTo;

  @Column(name = "resp_by", nullable = false)
  private Integer respBy;

  @Column(name = "RESP_BY_EMAIL", nullable = false)
  private String respByEmail;

  @Column(name = "RESP_BY_PHONE", nullable = false)
  private String respByPhone;

  @CreationTimestamp
  @Column(name = "resp_date", nullable = false)
  private ZonedDateTime resOn;

  @Column(name = "LISTING_ID", nullable = false)
  private Long listingId;

  @Column(name = "MESSAGE", nullable = false)
  private String message;
}
