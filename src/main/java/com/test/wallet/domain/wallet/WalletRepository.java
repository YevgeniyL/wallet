package com.test.wallet.domain.wallet;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
  @Query("select * from wallet w where w.account_id = :accountId")
  Optional<Wallet> findBy(@Param("accountId") Integer accountId);

  @Query("select * from wallet w where w.id = :id for update")
  Wallet getForUpdateBy(@Param("id") Integer walletId);

  /**
   * Modifies the balance of the wallet.
   * @param amount that should be subtracted or added to the wallet balance.
   */
  @Modifying
  @Query("update wallet w set w.balance = w.balance + :amount, w.updated_at = now() where w.id = :id")
  void modifyBalance(@Param("id") Integer id, @Param("amount") BigDecimal amount);
}
