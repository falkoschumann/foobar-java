/*
 * Hello World - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.contract.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {
  @NonNull String id;
  @NonNull String name;

  public User(String name) {
    this(UUID.randomUUID().toString(), name);
  }
}
