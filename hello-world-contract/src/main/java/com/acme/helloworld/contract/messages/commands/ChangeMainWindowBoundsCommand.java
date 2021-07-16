/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

import com.acme.helloworld.contract.data.Bounds;
import java.util.Objects;

public record ChangeMainWindowBoundsCommand(Bounds bounds) {
  public ChangeMainWindowBoundsCommand {
    Objects.requireNonNull(bounds, "bounds");
  }
}
