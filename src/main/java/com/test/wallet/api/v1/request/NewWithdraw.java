package com.test.wallet.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record NewWithdraw(
  @JsonProperty("accountId")
  @Positive Integer accountId,
  @JsonProperty("amount")
  @Positive BigDecimal amount
) {
}
