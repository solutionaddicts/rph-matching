package com.addicts.rph.matching.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author sravantatikonda
 */
public class RphUserInActiveException extends AuthenticationException {

  private static final long serialVersionUID = 7549527569338396719L;

  public RphUserInActiveException(String message) {

    super(message);
  }

  public RphUserInActiveException(String message, Throwable cause) {

    super(message, cause);

  }

}
