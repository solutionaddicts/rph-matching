package com.addicts.rph.matching.exception;


/**
 * @author sravantatikonda
 */
public class RphAuthenticationException extends RphException {

  private static final long serialVersionUID = -208510492811246705L;

  public RphAuthenticationException() {

  }

  public RphAuthenticationException(String message) {

    super(message);
  }

  public RphAuthenticationException(String message, Throwable cause) {

    super(message, cause);
  }
}
