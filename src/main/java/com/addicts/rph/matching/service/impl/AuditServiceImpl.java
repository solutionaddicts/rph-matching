package com.addicts.rph.matching.service.impl;

import com.addicts.rph.matching.dto.AuditDto;
import com.addicts.rph.matching.dto.AuditListRequestDto;
import com.addicts.rph.matching.dto.PageableImpl;
import com.addicts.rph.matching.entity.Audit;
import com.addicts.rph.matching.exception.RphException;
import com.addicts.rph.matching.repo.AuditRepository;
import com.addicts.rph.matching.security.JwtBody;
import com.addicts.rph.matching.service.AuditService;
import com.addicts.rph.matching.specification.AuditRecordsSpecification;
import com.addicts.rph.matching.util.ErrorMessages;
import com.addicts.rph.matching.util.PagedList;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author sravantatikonda
 */
@Service
//@Transactional(rollbackOn = Exception.class)
@Slf4j
@Data
public class AuditServiceImpl implements AuditService {

  @Autowired
  private AuditRepository auditRepository;

  @Autowired
  private MapperFacade mapperFacade;

  @Autowired
  private ErrorMessages errorMessages;

  /**
   * Creates an audit record
   *
   * @param entityId   the value for the modified or created entity
   * @param entityType the value for the type of entity
   */
  @Override
  public void createAuditRecord(Long entityId, String entityType, String description)
      throws RphException {
    Audit audit = new Audit();
    audit.setServiceName("RPH Main");
    if (SecurityContextHolder.getContext().getAuthentication()
        .getCredentials() instanceof JwtBody) {
      JwtBody jwtBody = (JwtBody) SecurityContextHolder.getContext().getAuthentication()
          .getCredentials();
      audit.setUserId(jwtBody.getUserId());
    }

    audit.setEntityId(entityId);
    audit.setEntityType(entityType);
    audit.setDescription(description);
    audit = auditRepository.save(audit);

    if (audit.getAuditId() == null) {
      log.error("Error while inserting an audit record for user: {}, and entity: {}",
          audit.getUserId(), audit.getEntityId());
    }
  }

  @Override
  public void createAuditRecordForNonLogin(Long entityId, Integer userId, String entityType,
      String description) throws RphException {
    Audit audit = new Audit();
    audit.setServiceName("RPH Main");
    audit.setUserId(userId);
    audit.setEntityId(entityId);
    audit.setEntityType(entityType);
    audit.setDescription(description);
    audit = auditRepository.save(audit);

    if (audit.getAuditId() == null) {
      log.error("Error while inserting an audit record for user: {}, and entity: {}",
          audit.getUserId(), audit.getEntityId());
    }
  }

  /**
   * Returns a list of audit rows created by the user
   *
   * @param userId the identifier of the requesting user
   * @return the list of values for {@link AuditDto}
   */
  @Override
  public List<AuditDto> getAuditRecords(Integer userId) {
    List<Audit> auditEntities = auditRepository
        .findAllById(Collections.singletonList(userId));
    return mapperFacade.mapAsList(auditEntities, AuditDto.class);
  }

  /**
   * Returns a list of audit records of all the users for admin auditing
   *
   * @return the list of values for {@link AuditDto}
   */
  @Override
  public PagedList<AuditDto> getAllAuditRecords(PageableImpl pageable,
      AuditListRequestDto auditListRequestDto) throws RphException {
    AuditRecordsSpecification auditRecordsSpecification = new AuditRecordsSpecification(
        auditListRequestDto);

    if (pageable.getPage() == null) {
      pageable.setPage(0);
    } else {
      pageable.setPage(pageable.getPage() - 1);
    }
    if (pageable.getSize() == null) {
      pageable.setSize(10);
    }

    Pageable pageable2 = PageRequest
        .of(pageable.getPage(), pageable.getSize(), PagedList.getSorts(pageable.getSort()));

    Page<Audit> pagedList = auditRepository.findAll(auditRecordsSpecification, pageable2);

    List<AuditDto> auditDtos = mapperFacade
        .mapAsList(pagedList.getContent(), AuditDto.class);
    return new PagedList<>(auditDtos, pageable2, pagedList.getTotalElements());
  }
}
