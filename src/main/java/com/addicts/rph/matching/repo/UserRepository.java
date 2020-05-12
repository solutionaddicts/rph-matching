package com.addicts.rph.matching.repo;

import com.addicts.rph.matching.entity.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author sravantatikonda
 */
public interface UserRepository extends IBaseRepository<UserModel, Integer>,
    JpaSpecificationExecutor<UserModel> {

  Optional<UserModel> findByEmail(String email);

//  Optional<UserModel> findByUserName(String email);
}
