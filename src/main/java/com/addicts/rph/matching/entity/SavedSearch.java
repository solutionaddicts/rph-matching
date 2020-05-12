package com.addicts.rph.matching.entity;

import com.addicts.rph.matching.dto.RentalPropertiesListRequestDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author sravantatikonda
 */
@Data
@Table(name = "RPH_SAVED_SEARCH")
@EntityListeners(AuditingEntityListener.class)
@Entity
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class SavedSearch implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer savedSearchId;

  @Column(name = "USER_ID")
  private Integer userId;

  @Column(name = "FILTER_BODY")
  @Type(type = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private RentalPropertiesListRequestDto filterBody;

  @Column(name = "Instant_Update")
  private Boolean instantUpdate;

  @Column(name = "Daily_Update")
  private Boolean dailyUpdate;

  @Column(name = "No_Update")
  private Boolean noUpdate;

  @CreationTimestamp
  @Column(name = "CREATED_AT")

  private ZonedDateTime createdAt;
}
