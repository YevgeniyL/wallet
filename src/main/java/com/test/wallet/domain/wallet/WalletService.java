package com.test.wallet.domain.wallet;

import com.test.wallet.api.v1.request.NewDeposit;
import com.test.wallet.api.v1.request.NewWithdraw;
import com.test.wallet.exception.NotEnoughBalanceException;
import com.test.wallet.exception.WalletNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class WalletService {
  private final WalletRepository walletRepository;
  private final TransactionTemplate transactionTemplate;

  public WalletService(WalletRepository walletRepository, TransactionTemplate transactionTemplate) {
    this.walletRepository = walletRepository;
    this.transactionTemplate = transactionTemplate;
  }

  public BigDecimal perform(NewDeposit deposit) {
    Integer walletId = walletRepository
      .findBy(deposit.accountId())
      .map(wallet -> wallet.id)
      .orElseThrow(() -> new WalletNotFoundException());

    return transactionTemplate.execute(status -> {
      BigDecimal balance = walletRepository.getForUpdateBy(walletId).balance;

      walletRepository.modifyBalance(walletId, deposit.amount());

      return balance.add(deposit.amount());
    });
  }

  public BigDecimal perform(NewWithdraw withdraw) {
    Integer walletId = walletRepository
      .findBy(withdraw.accountId())
      .map(wallet -> wallet.id)
      .orElseThrow(() -> new WalletNotFoundException());

    return transactionTemplate.execute(status -> {
      BigDecimal balance = walletRepository.getForUpdateBy(walletId).balance;

      if (balance.compareTo(withdraw.amount()) < 0) {
        throw new NotEnoughBalanceException();
      }

      walletRepository.modifyBalance(walletId, withdraw.amount().negate());

      return balance.subtract(withdraw.amount());
    });
  }
}
