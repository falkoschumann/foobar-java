/*
 * Foobar - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.contract.messages.commands;

import java.util.Objects;

public record Failure(String errorMessage) implements CommandStatus {
  public Failure {
    Objects.requireNonNull(errorMessage, "errorMessage");
  }
}
