package com.addicts.rph.matching.exception;

/**
 * @author sravantatikonda
 */
public class RphException extends Exception {

  private static final long serialVersionUID = 8048660952713189376L;

  public RphException() {
  }

  public RphException(String message) {
    super(message);
  }

  public RphException(String message, Throwable cause) {
    super(message, cause);
  }

  public RphException(Throwable cause) {
    super(cause);
  }

  public RphException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
