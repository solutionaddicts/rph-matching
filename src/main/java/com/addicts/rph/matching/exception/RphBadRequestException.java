package com.addicts.rph.matching.exception;


public class RphBadRequestException extends RphException {

  private static final long serialVersionUID = 1891202591117671840L;

  public RphBadRequestException() {

  }

  public RphBadRequestException(String message) {

    super(message);
  }

  public RphBadRequestException(String message, Throwable cause) {

    super(message, cause);
  }
}