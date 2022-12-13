package com.test.wallet.domain.account;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("account")
public class Account {
  @Id
  Integer id;
  String email;
  String password;
  LocalDateTime createdAt;
}
