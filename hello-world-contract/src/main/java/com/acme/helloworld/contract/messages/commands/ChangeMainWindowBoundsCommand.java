/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

import com.acme.helloworld.contract.data.WindowBounds;
import java.util.Objects;

public record ChangeMainWindowBoundsCommand(WindowBounds windowBounds) {
  public ChangeMainWindowBoundsCommand {
    Objects.requireNonNull(windowBounds, "windowBounds");
  }
}
