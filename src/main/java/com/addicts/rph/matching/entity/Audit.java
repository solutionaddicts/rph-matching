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
@Table(name = "RPH_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class Audit implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer auditId;

  @Column(name = "USER_ID", nullable = false)
  private Integer userId;

  @Column(name = "CREATE_DT", nullable = false)
  @CreationTimestamp
  private ZonedDateTime createDt;

  @Column(name = "ENTITY_ID", nullable = false)
  private Long entityId;

  @Column(name = "ENTITY_TYPE", nullable = false)
  private String entityType;

  @Column(name = "describe")
  private String description;

  @Column(name = "SVC_NAME", nullable = false)
  private String serviceName;
}
