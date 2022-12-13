package com.test.wallet.api.v1.responce;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record Balance(
  @JsonProperty("amount")
  @PositiveOrZero
  BigDecimal amount
) {
}
