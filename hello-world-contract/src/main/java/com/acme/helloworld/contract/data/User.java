/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

import java.util.Objects;
import java.util.UUID;

public record User(String id, String name) {
  public User {
    Objects.requireNonNull(id, "id");
    Objects.requireNonNull(name, "name");
  }

  public User(String name) {
    this(UUID.randomUUID().toString(), name);
  }
}
