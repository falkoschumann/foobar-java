package com.acme.helloworld.backend.events;

import com.acme.helloworld.backend.Event;
import java.time.Instant;
import java.util.Objects;

public record UserCreatedEvent(String id, Instant timestamp, String name) implements Event {
public UserCreatedEvent{
  Objects.requireNonNull(id, "id");
  Objects.requireNonNull(timestamp, "timestamp");
  Objects.requireNonNull(name, "name");
}
}
