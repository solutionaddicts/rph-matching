package com.addicts.rph.matching.specification;

import com.addicts.rph.matching.dto.RenterProfileDto;
import com.addicts.rph.matching.entity.RenterProfile;
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
public class RenterProfileSpecification implements Specification<RenterProfile> {

  private static final long serialVersionUID = 1L;

  private RenterProfileDto searchCriteria;

  public RenterProfileSpecification(RenterProfileDto searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<RenterProfile> root, CriteriaQuery<?> arg1,
      CriteriaBuilder cb) {

    final List<Predicate> predicates = new ArrayList<>();

    /*if (searchCriteria.getProfessionalUserId() != null) {
      predicates
          .add(cb.equal(root.get("professionalUserId"), searchCriteria.getProfessionalUserId()));
    }

    // Filter for min avg rating acquired by the professional
    if (searchCriteria.getRequestStatus() != null) {
      predicates
          .add(cb.equal(root.get("requestStatus"), searchCriteria.getRequestStatus()));
    }*/

    //TODO: Sort by support for create date
    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
