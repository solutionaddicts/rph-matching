package com.addicts.rph.matching.entity;

import com.addicts.rph.matching.crypto.CryptoConverter;
import com.addicts.rph.matching.dto.Address;
import com.addicts.rph.matching.dto.NotificationSettings;
import com.addicts.rph.matching.enums.AuthorityProvider;
import com.addicts.rph.matching.enums.UserType;
import com.addicts.rph.matching.enums.UserTypeConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

/**
 * @author sravantatikonda
 */
@Data
@Entity
@Table(name = "RPH_USER")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class UserModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //@SequenceGenerator(name = "mySeqGen", sequenceName = "RPH_USER_USER_ID", initialValue = 11, allocationSize = 100)
  //@GeneratedValue(generator = "mySeqGen")
  @Column(name = "USER_ID", nullable = false)
  private Integer userId;

  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;

  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;

  @Column(name = "EMAIL", nullable = false)
  @Convert(converter = CryptoConverter.class)
  private String email;

  @Column(name = "USER_TYPE", nullable = false)
  @Convert(converter = UserTypeConverter.class)
  private UserType userType;

  @Column(name = "PHONE_NUM", nullable = false)
  @Convert(converter = CryptoConverter.class)
  private String phoneNum;

  @Type(type = "jsonb")
  @Column(name = "ADDR", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Address address;

  @Type(type = "jsonb")
  @Column(name = "FAV_ADDR_1", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Address favAddress1;

  @Type(type = "jsonb")
  @Column(name = "FAV_ADDR_2", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Address favAddress2;

  @Type(type = "jsonb")
  @Column(name = "NOTIFICATIONS_SETTINGS", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private NotificationSettings notificationSettings;

  @Column(name = "AVG_RATING", nullable = false)
  private Double averageRating;

  @Column(name = "NUM_REVIEWS", nullable = false)
  private Integer totalReviews;

  @Column(name = "JOBS_COMP", nullable = false)
  private Integer jobsCompleted;

  @Type(type = "jsonb")
  @Column(name = "SPECIALITIES", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Specialities specialities;

  @Column(name = "COMPANY", nullable = false)
  private String company;

  @Column(name = "HOURS_AVAIL", nullable = false)
  private String availableHours;

  @Type(type = "jsonb")
  @Column(name = "reviews_received", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Reviews reviewsReceived;

  @Type(type = "jsonb")
  @Column(name = "reviews_given", columnDefinition = "jsonb")
  @Basic(fetch = FetchType.LAZY)
  private Reviews reviewsGiven;

  @Column(name = "created_on", nullable = false)
  @CreationTimestamp
  private ZonedDateTime createdOn;

  @Column(name = "authority_provider", nullable = false)
  @Enumerated(EnumType.STRING)
  private AuthorityProvider authorityProvider;
}
