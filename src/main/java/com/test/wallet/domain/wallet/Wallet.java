package com.test.wallet.domain.wallet;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ZERO;

@Table("wallet")
public class Wallet {
  @Id
  public Integer id;
  public Integer accountId;
  public BigDecimal balance;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;
}
