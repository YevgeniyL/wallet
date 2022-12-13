package com.test.wallet.controller;

import com.test.wallet.api.v1.request.NewDeposit;
import com.test.wallet.api.v1.request.NewWithdraw;
import com.test.wallet.api.v1.responce.Balance;
import com.test.wallet.domain.wallet.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WalletController {
  private final WalletService walletService;

  public WalletController(WalletService walletService) {
    this.walletService = walletService;
  }

  @PutMapping(value = "/wallet/deposit", consumes = APPLICATION_JSON_VALUE)
  public Balance deposit(@Valid @RequestBody NewDeposit deposit) {
    return new Balance(walletService.perform(deposit));
  }

  @PutMapping(value = "/wallet/withdraw", consumes = APPLICATION_JSON_VALUE)
  public Balance withdraw(@Valid @RequestBody NewWithdraw withdraw) {
    return new Balance(walletService.perform(withdraw));
  }
}
