package com.addicts.rph.matching.repo;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
