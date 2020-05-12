package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ResetPassword implements Serializable {

  private static final long serialVersionUID = 1L;

  private String resetPasswordToken;

  private String newPassword;
}
