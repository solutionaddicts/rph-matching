package com.addicts.rph.matching.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "{validation.notEmpty.address}")
  private String address1;

  private String address2;

  private String locality;

  private String landmark;

  @NotBlank(message = "{validation.notEmpty.city}")
  private String city;

  private String county;

  @NotBlank(message = "{validation.notEmpty.state}")
  private String state;

  @NotBlank(message = "{validation.notEmpty.zipCode}")
  private String zipCode;

  @NotBlank(message = "{validation.notEmpty.country}")
  private String country;

  private String lat;

  private String lng;
}
