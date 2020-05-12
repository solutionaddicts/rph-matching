package com.addicts.rph.matching.exception;


/**
 * @author sravantatikonda
 */
public class RphExceptionResponse {

  private String errorType;

  private int code;

  private String message;

  public RphExceptionResponse() {
  }

  public RphExceptionResponse(String errorType, int code, String message) {
    this.errorType = errorType;
    this.code = code;
    this.message = message;
  }

  public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
