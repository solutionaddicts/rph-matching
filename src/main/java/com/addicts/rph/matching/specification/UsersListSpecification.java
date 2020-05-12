package com.addicts.rph.matching.specification;

import com.addicts.rph.matching.dto.UserDto;
import com.addicts.rph.matching.entity.UserModel;
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
public class UsersListSpecification implements Specification<UserModel> {

  private static final long serialVersionUID = 1L;

  private UserDto searchCriteria;

  public UsersListSpecification(UserDto searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> arg1,
      CriteriaBuilder cb) {

    final List<Predicate> predicates = new ArrayList<>();

    if (searchCriteria.getUserType() != null) {
      predicates
          .add(cb.equal(root.get("userType"), searchCriteria.getUserType()));
    }

    return cb.and(predicates.toArray(new Predicate[0]));
  }
}
