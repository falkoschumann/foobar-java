/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.events;

import com.acme.helloworld.backend.Event;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record UserCreatedEvent(String id, Instant timestamp, String name) implements Event {
  public UserCreatedEvent {
    Objects.requireNonNull(id, "id");
    Objects.requireNonNull(timestamp, "timestamp");
    Objects.requireNonNull(name, "name");
  }

  public UserCreatedEvent(String name) {
    this(UUID.randomUUID().toString(), Instant.now(), name);
  }
}
