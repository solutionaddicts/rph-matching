package com.addicts.rph.matching.entity;

import com.addicts.rph.matching.crypto.CryptoConverter;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "RPH_USER_AUTH")
public class UserAuth implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AUTH_ID", nullable = false)
  private Integer authId;

  @Column(name = "USER_ID", nullable = false)
  private Integer userId;

  @Column(name = "PASSWORD", nullable = true)
  private String password;

  @Column(name = "EMAIL", nullable = false)
  @Convert(converter = CryptoConverter.class)
  private String email;

  @Column(name = "IS_ACTIVE", nullable = false)
  private Boolean isActive;

  @Column(name = "FAILS_SINCE_LOGIN")
  private Integer failuresSinceLogin;

  @Column(name = "LAST_LOGIN")
  private ZonedDateTime lastLogin;

  @Column(name = "LAST_LOGOUT")
  private ZonedDateTime lastLogout;

  @Column(name = "AUTO_RESET_DATE")
  private ZonedDateTime autoResetDate;

  @Column(name = "reset_password_token")
  private String resetPasswordToken;
}
