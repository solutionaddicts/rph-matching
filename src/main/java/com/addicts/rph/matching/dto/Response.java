package com.addicts.rph.matching.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class Response<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty(value = "responseBody")
  private T response;

  public Response(T response) {
    this.response = response;
  }

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }
}
