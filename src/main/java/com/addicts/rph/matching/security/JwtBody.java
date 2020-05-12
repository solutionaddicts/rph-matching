package com.addicts.rph.matching.security;

import java.time.ZonedDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class JwtBody {

  private String firstname;

  private String lastname;

  private Integer userId;

  private String email;

  private ZonedDateTime expiryTime;

  private List<String> scope;

  private String clientId;
}
