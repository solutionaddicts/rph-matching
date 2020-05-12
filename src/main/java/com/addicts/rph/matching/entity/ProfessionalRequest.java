package com.addicts.rph.matching.entity;

import com.addicts.rph.matching.dto.Address;
import com.addicts.rph.matching.enums.ProfReqTypeStatusConverter;
import com.addicts.rph.matching.enums.ProfRequestStatusType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "RPH_PROF_REQS")
@EntityListeners(AuditingEntityListener.class)
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class ProfessionalRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  @Id
  private Integer requestId;

  @Column(name = "REQ_BY_ID", nullable = false)
  private Integer requestedById;

  @CreationTimestamp
  @Column(name = "REQUESTED_ON", nullable = false)
  private ZonedDateTime requestedOn;

  @UpdateTimestamp
  @Column(name = "UPDATED_ON", nullable = false)
  private ZonedDateTime updatedOn;

  @Column(name = "REPAIR_SPECIALITY", nullable = false)
  private String reqSpeciality;

  @Column(name = "NOTES", nullable = false)
  private String notes;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Type(type = "jsonb")
  @Column(name = "ADDRESS", nullable = false)
  private Address requestLocation;

  @Column(name = "required_on", nullable = false)
  private ZonedDateTime requiredOn;

  /**
   * A request status can be in New, Accepted, Rejected, OnGoing, Completed
   */
  @Column(name = "STATUS", nullable = false)
  @Convert(converter = ProfReqTypeStatusConverter.class)
  private ProfRequestStatusType requestStatus;

  @Column(name = "prof_user_id", nullable = false)
  private Integer professionalUserId;
}
