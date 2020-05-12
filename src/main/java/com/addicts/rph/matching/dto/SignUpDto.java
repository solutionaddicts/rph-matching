package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class SignUpDto extends UserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String password;
}
