package com.test.wallet.exception;

public class WalletNotFoundException extends RuntimeException {
  @Override public String getMessage() {
    return "Wallet not found";
  }
}
