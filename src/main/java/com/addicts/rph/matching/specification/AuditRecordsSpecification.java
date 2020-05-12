package com.addicts.rph.matching.specification;

import com.addicts.rph.matching.dto.AuditListRequestDto;
import com.addicts.rph.matching.entity.Audit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author sravantatikonda
 */
public class AuditRecordsSpecification implements Specification<Audit> {

  private static final long serialVersionUID = 1L;

  private AuditListRequestDto searchCriteria;

  public AuditRecordsSpecification(AuditListRequestDto searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<Audit> root, CriteriaQuery<?> arg1,
      CriteriaBuilder cb) {

    final List<Predicate> predicates = new ArrayList<>();

    if (searchCriteria.getUserId() != null) {
      predicates.add(cb.equal(root.get("userId"), searchCriteria.getUserId()));
    }

    if (searchCriteria.getEntityId() != null) {
      predicates.add(cb.equal(root.get("entityId"), searchCriteria.getEntityId()));
    }

    if (searchCriteria.getEntityType() != null) {
      predicates.add(cb.equal(root.get("entityType"), searchCriteria.getEntityType()));
    }

    return cb.and(predicates.toArray(new Predicate[0]));
  }
}
