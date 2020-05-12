package com.addicts.rph.matching.exception;


/**
 * @author sravantatikonda
 */
public class RphInvalidTokenException extends RphException {

  private static final long serialVersionUID = -1900286432698769622L;

  public RphInvalidTokenException() {

  }

  public RphInvalidTokenException(String message) {

    super(message);
  }

  public RphInvalidTokenException(String message, Throwable cause) {

    super(message, cause);
  }
}
