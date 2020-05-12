package com.addicts.rph.matching.dto;

import java.io.Serializable;
import javax.validation.Valid;
import lombok.Data;

@Data
public class Request<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private T payLoad;

  public T getPayLoad() {
    return payLoad;
  }

  public void setPayLoad(T payLoad) {
    this.payLoad = payLoad;
  }
}
