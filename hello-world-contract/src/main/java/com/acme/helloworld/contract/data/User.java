/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

import java.util.Objects;

public record User(String name) {
  public User {
    Objects.requireNonNull(name, "name");
  }
}
