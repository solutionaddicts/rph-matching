package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class NotificationSettings implements Serializable {

  private static final long serialVersionUID = 1L;

  private Boolean newsLettersAndUpdates;

  private Boolean serviceStatusUpdates;

  private Boolean preServiceReminders;

  private Boolean paymentRemainders;

  private Boolean specialOffers;

  private Boolean unsubscribeAll;
}
