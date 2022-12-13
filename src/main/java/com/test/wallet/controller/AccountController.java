package com.test.wallet.controller;

import com.test.wallet.api.v1.request.NewAccount;
import com.test.wallet.domain.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PutMapping("/account")
  public Integer create(@Valid @RequestBody NewAccount newAccount) {
    return accountService.create(newAccount);
  }
}
