package com.addicts.rph.matching.exception;


/**
 * @author sravantatikonda
 */
public class RphNotFoundException extends RphException {

  private static final long serialVersionUID = -5395542384592074395L;

  public RphNotFoundException() {

  }

  public RphNotFoundException(String message) {

    super(message);
  }

  public RphNotFoundException(String message, Throwable cause) {

    super(message, cause);
  }
}