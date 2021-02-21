/*
 * User Stories - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.contract.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class User {
  String id;
  String name;

  public User(String name) {
    this(UUID.randomUUID().toString(), name);
  }
}
