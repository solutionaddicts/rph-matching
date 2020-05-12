package com.addicts.rph.matching.service;

import com.addicts.rph.matching.dto.AuditDto;
import com.addicts.rph.matching.dto.AuditListRequestDto;
import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.util.PagedList;
import java.util.List;

public interface AuditService {

  /**
   * Creates an audit record
   *
   * @param entityId   the value for the modified or created entity
   * @param entityType the value for the type of entity
   */
  void createAuditRecord(Long entityId, String entityType, String description)
      throws RphException;

  /**
   * Creates an audit record
   *
   * @param entityId   the value for the modified or created entity
   * @param entityType the value for the type of entity
   */
  void createAuditRecordForNonLogin(Long entityId, Integer userId, String entityType,
      String description)
      throws RphException;

  /**
   * Returns a list of audit rows created by the user
   *
   * @param userId the identifier of the requesting user
   * @return the list of values for {@link AuditDto}
   */
  List<AuditDto> getAuditRecords(Integer userId) throws RphException;

  /**
   * Returns a list of audit records of all the users for admin auditing
   *
   * @return the list of values for {@link AuditDto}
   */
  PagedList<AuditDto> getAllAuditRecords(PageableImpl pageable,
      AuditListRequestDto auditListRequestDto) throws RphException;
}
