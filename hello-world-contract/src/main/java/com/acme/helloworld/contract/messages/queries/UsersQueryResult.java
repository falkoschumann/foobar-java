/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.queries;

import com.acme.helloworld.contract.data.User;
import java.util.List;
import java.util.Objects;

public record UsersQueryResult(List<User> users) {
  public UsersQueryResult{
    Objects.requireNonNull(users,"users");
  }
}
