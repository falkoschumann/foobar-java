/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.queries;

import java.util.Objects;

public record PreferencesQueryResult(String eventStreamFile) {
  public PreferencesQueryResult {
    Objects.requireNonNull(eventStreamFile, "eventStreamFile");
  }
}
