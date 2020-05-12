package com.addicts.rph.matching.dto;

import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class CredentialsRequest {

  private String email;

  private String currentPassword;

  private String newPassword;
}
