package com.addicts.rph.matching.repo;

import com.addicts.rph.matching.entity.Audit;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sravantatikonda
 */
public interface AuditRepository extends IBaseRepository<Audit, Integer>,
    JpaSpecificationExecutor<Audit> {

}
