package com.test.wallet.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record NewDeposit(
  @JsonProperty("accountId")
  @Positive Integer accountId,
  @JsonProperty("amount")
  @Positive BigDecimal amount
) {
}
