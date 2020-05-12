package com.addicts.rph.matching.exception;

/**
 * @author sravantatikonda
 */
public class RphValidationException extends RphException {

  private static final long serialVersionUID = 7549527569338396719L;

  public RphValidationException() {

  }

  public RphValidationException(String message) {

    super(message);
  }

  public RphValidationException(String message, Throwable cause) {

    super(message, cause);

  }

}
