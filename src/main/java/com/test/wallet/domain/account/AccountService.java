package com.test.wallet.domain.account;

import com.test.wallet.api.v1.request.NewAccount;
import com.test.wallet.domain.wallet.Wallet;
import com.test.wallet.domain.wallet.WalletRepository;
import com.test.wallet.exception.AccountEmailExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDateTime.now;

@Service
public class AccountService {
  private final TransactionTemplate transactionTemplate;
  private final AccountRepository accountRepository;
  private final WalletRepository walletRepository;


  public AccountService(TransactionTemplate transactionTemplate, AccountRepository accountRepository,
                        WalletRepository walletRepository) {
    this.transactionTemplate = transactionTemplate;
    this.accountRepository = accountRepository;
    this.walletRepository = walletRepository;
  }

  public Integer create(NewAccount newAccount) {
    var email = newAccount.email().trim().toLowerCase();

    accountRepository.findBy(email)
                     .ifPresent(account -> {
                       throw new AccountEmailExistException();
                     });

    return transactionTemplate.execute(status -> {
      var account = new Account();
      account.createdAt = now();
      account.email = email;
      account.password = newAccount.password();
      Integer accountId = accountRepository.save(account).id;

      var wallet = new Wallet();
      wallet.accountId = accountId;
      wallet.balance = ZERO;
      wallet.createdAt = now();
      wallet.updatedAt = now();
      walletRepository.save(wallet);

      return accountId;
    });
  }
}
