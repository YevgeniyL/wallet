package com.test.wallet.api.v1.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record NewAccount(
  @Email
  @JsonProperty("email")
  @NotBlank String email,
  @JsonProperty("password")
  @NotBlank String password
) {
}
