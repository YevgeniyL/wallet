package com.test.wallet.domain.account;

import com.test.wallet.api.v1.request.NewAccount;
import com.test.wallet.domain.wallet.Wallet;
import com.test.wallet.domain.wallet.WalletRepository;
import com.test.wallet.exception.AccountEmailExistException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountServiceTest {
  private final TransactionTemplate transactionTemplate = new TransactionTemplate(new PseudoTransactionManager());
  private final AccountRepository accountRepository = mock(AccountRepository.class);
  private final WalletRepository walletRepository = mock(WalletRepository.class);
  private final AccountService service = new AccountService(transactionTemplate, accountRepository, walletRepository);

  @Test
  void create_throw_AccountEmailExistException() {
    Account account = new Account();
    account.email = "123@gmail.com";
    when(accountRepository.findBy("123@gmail.com")).thenReturn(Optional.of(account));

    assertThrows(AccountEmailExistException.class,
                 () -> service.create(new NewAccount("123@Gmail.com", "abcDEF")));
  }

  @Test
  void create() {
    when(accountRepository.findBy("123@gmail.com")).thenReturn(Optional.empty());

    var account = new Account();
    account.id = 1;
    account.createdAt = now();
    account.email = "123@gmail.com";
    account.password = "abcDEF";
    when(accountRepository.save(any())).thenReturn(account);

    Integer accountId = service.create(new NewAccount("123@Gmail.com", "abcDEF"));
    assertEquals(1, accountId);

    ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
    verify(accountRepository).save(captor.capture());
    assertThat(captor.getValue().id).isNull();
    assertThat(captor.getValue().createdAt).isNotNull();
    assertThat(captor.getValue().email).isEqualTo("123@gmail.com");
    assertThat(captor.getValue().password).isEqualTo("abcDEF");

    ArgumentCaptor<Wallet> walletCaptor = ArgumentCaptor.forClass(Wallet.class);
    verify(walletRepository).save(walletCaptor.capture());
    assertThat(walletCaptor.getValue().id).isNull();
    assertThat(walletCaptor.getValue().accountId).isEqualTo(1);
    assertThat(walletCaptor.getValue().balance).isEqualTo(ZERO);
    assertThat(walletCaptor.getValue().createdAt).isNotNull();
    assertThat(walletCaptor.getValue().updatedAt).isNotNull();
  }
}
