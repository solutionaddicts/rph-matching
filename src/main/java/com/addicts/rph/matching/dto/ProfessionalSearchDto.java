package com.addicts.rph.matching.dto;

import com.addicts.rph.matching.entity.Specialities;
import com.addicts.rph.matching.enums.UserType;
import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class ProfessionalSearchDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer userId;

  private String firstName;

  private String lastName;

  private UserType userType;

  private Specialities specialities;

  private Double averageRating;

  private Integer jobsCompleted;

  private Integer totalReviews;

  private String company;

  private Address address;

  private String phoneNum;

  private String email;

  private String availableHours;
}
