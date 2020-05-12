package com.addicts.rph.matching.exception;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author sravantatikonda
 */
@Data
public class RphApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;


  public RphApiError() {
    super();
  }

  public RphApiError(final HttpStatus status, final String message, final List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  public RphApiError(final HttpStatus status, final String message, final String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }


}