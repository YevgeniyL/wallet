package com.test.wallet.domain.account;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class PseudoTransactionManager extends AbstractPlatformTransactionManager {

  private static final long serialVersionUID = 1L;

  @Override
  protected Object doGetTransaction() throws TransactionException {
    return new Object();
  }

  @Override
  protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
    //noop
  }

  @Override
  protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
    //noop
  }

  @Override
  protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
    //noop
  }
}
