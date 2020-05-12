package com.addicts.rph.matching.entity;

import com.addicts.rph.matching.dto.StringList;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "rph_renter_profile")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class RenterProfile implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer rentalProfId;

  @Column(name = "USER_ID", nullable = false)
  private Integer userId;

  /**
   * No. of tenants moving in
   */
  @Column(name = "num_tenants", nullable = false)
  private Integer numTenants;

  @Column(name = "tentative_move_in_date", nullable = false)
  private ZonedDateTime tentativeMoveInDate;

  @Column(name = "smoke_friendly", nullable = false)
  private Boolean smokeFriendly;

  @Column(name = "credit_hist_range", nullable = false)
  private String creditHistoryRange;

  @Column(name = "pets_allowed")
  private Boolean arePetsAllowed;

  @Column(name = "pets", nullable = false)
  @Type(type = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private StringList pets;

  @Column(name = "single_room", nullable = false)
  private Boolean singleRoom;

  @Column(name = "roommates_comfortable", nullable = false)
  private Integer numRoommatesComfort;

  @Column(name = "work_zip", nullable = false)
  private Integer workZipCode;

  @Column(name = "company_name", nullable = false)
  private String companyName;

  @Column(name = "income_range", nullable = false)
  private String incomeRange;
}
