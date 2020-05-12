package com.addicts.rph.matching.exception;

/**
 * @author sravantatikonda
 */
public class RphBaseRuntimeException extends RuntimeException {

  private static final long serialVersionUID = -2605328210144803059L;

  public RphBaseRuntimeException() {
  }

  public RphBaseRuntimeException(String message) {
    super(message);
  }

  public RphBaseRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public RphBaseRuntimeException(Throwable cause) {
    super(cause);
  }

  public RphBaseRuntimeException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
