/*
 * Foobar - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.contract.messages.commands;

import lombok.NonNull;
import lombok.Value;

@Value
public class CreateUserCommand {
  @NonNull String name;
}
