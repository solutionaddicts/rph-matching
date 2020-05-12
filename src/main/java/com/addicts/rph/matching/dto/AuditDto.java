package com.addicts.rph.matching.dto;

import java.time.ZonedDateTime;
import lombok.Data;

/**
 * @author sravantatikonda
 */
@Data
public class AuditDto {

  private static final long serialVersionUID = 1L;

  private Integer userId;

  private ZonedDateTime createDt;

  private Long entityId;

  private String entityType;

  private String serviceName;
}
