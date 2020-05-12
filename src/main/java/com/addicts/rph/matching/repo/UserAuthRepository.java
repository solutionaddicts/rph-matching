package com.addicts.rph.matching.repo;

import com.addicts.rph.matching.entity.UserAuth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author sravantatikonda
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

  @Query("SELECT DISTINCT userAuth FROM UserAuth userAuth " +
      "INNER JOIN UserModel AS userModel ON userModel.userId = userAuth.userId " +
      "WHERE userAuth.resetPasswordToken = :resetPasswordToken AND userModel.authorityProvider = 'RPH'")
  Optional<UserAuth> findByResetPasswordToken(String resetPasswordToken);

  @Query("SELECT DISTINCT userAuth FROM UserAuth userAuth " +
      "INNER JOIN UserModel AS userModel ON userModel.userId = userAuth.userId " +
      "WHERE userAuth.email = :email AND userModel.authorityProvider = 'RPH'")
  Optional<UserAuth> findByEmail(String email);
}
