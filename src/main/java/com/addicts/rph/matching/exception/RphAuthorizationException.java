package com.addicts.rph.matching.exception;


/**
 * @author sravantatikonda
 */
public class RphAuthorizationException extends RphException {

  private static final long serialVersionUID = -8672303480489297385L;

  public RphAuthorizationException() {

  }

  public RphAuthorizationException(String message) {

    super(message);
  }

  public RphAuthorizationException(String message, Throwable cause) {

    super(message, cause);
  }
}
