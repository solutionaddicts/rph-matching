package com.addicts.rph.matching.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class SavedSearchDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer savedSearchId;

  private Integer userId;

  private RentalPropertiesListRequestDto filterBody;

  private Boolean instantUpdate;

  private Boolean dailyUpdate;

  private Boolean noUpdate;
}
