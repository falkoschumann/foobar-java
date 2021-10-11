/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.queries;

import com.acme.helloworld.contract.data.User;

public record NewestUserQueryResult(User user, String errorMessage) {
  public NewestUserQueryResult() {
    this(null, null);
  }

  public NewestUserQueryResult(User user) {
    this(user, null);
  }

  public NewestUserQueryResult(String errorMessage) {
    this(null, errorMessage);
  }

  public boolean succeeded() {
    return errorMessage == null;
  }

  public boolean failed() {
    return errorMessage != null;
  }
}
