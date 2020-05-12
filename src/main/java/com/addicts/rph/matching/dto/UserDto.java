package com.addicts.rph.matching.dto;

import com.addicts.rph.matching.entity.Reviews;
import com.addicts.rph.matching.entity.Specialities;
import com.addicts.rph.matching.enums.UserType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class UserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer userId;

  private String firstName;

  private String lastName;

  private String email;

  /**
   * A user type can be Agent/Electrician/Plumber/Broker/Owner or Tenant/HomeCleaning/PestControl/Photographer”,
   */
  private UserType userType;

  private String phoneNum;

  private Address address;

  private Address favAddress1;

  private Address favAddress2;

  private NotificationSettings notificationSettings;

  private Double averageRating;

  private Integer totalReviews;

  private Integer jobsCompleted;

  private Specialities specialities;

  private String company;

  private String availableHours;

  private Reviews reviewsReceived;

  private Reviews reviewsGiven;

  private ZonedDateTime createdOn;
}
