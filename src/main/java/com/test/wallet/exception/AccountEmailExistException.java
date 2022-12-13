package com.test.wallet.exception;

public class AccountEmailExistException extends RuntimeException {
  @Override public String getMessage() {
    return "Current email is existed.";
  }
}
