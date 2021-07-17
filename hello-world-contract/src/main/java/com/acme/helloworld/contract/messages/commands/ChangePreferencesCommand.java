/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

import java.util.Objects;

public record ChangePreferencesCommand(String greeting) {
  public ChangePreferencesCommand {
    Objects.requireNonNull(greeting, "greeting");
  }
}
