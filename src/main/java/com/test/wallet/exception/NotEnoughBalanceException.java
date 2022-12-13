package com.test.wallet.exception;

public class NotEnoughBalanceException extends RuntimeException {
  @Override public String getMessage() {
    return "Not enough balance";
  }
}
