/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.queries;

import com.acme.helloworld.contract.data.WindowBounds;
import java.util.Objects;

public record MainWindowBoundsQueryResult(WindowBounds windowBounds) {
  public MainWindowBoundsQueryResult {
    Objects.requireNonNull(windowBounds, "windowBounds");
  }
}
