/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

import java.util.Objects;

public record User(String id, String name) {
  public User {
    Objects.requireNonNull(id, "id");
    Objects.requireNonNull(name, "name");
  }
}
