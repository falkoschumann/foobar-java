/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

import com.acme.helloworld.contract.data.DatabaseConnection;
import java.util.Objects;

public record ChangePreferencesCommand(DatabaseConnection databaseConnection) {
  public ChangePreferencesCommand{
    Objects.requireNonNull(databaseConnection,"databaseConnection");
  }
}
