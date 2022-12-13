package com.test.wallet.domain.account;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
  @Query("select * from account a where a.email = :email")
  Optional<Account> findBy(@Param("email") String email);
}
