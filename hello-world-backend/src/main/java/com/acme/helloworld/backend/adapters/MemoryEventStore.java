/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.Event;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MemoryEventStore extends AbstractEventStore {
  private final List<Event> events = new ArrayList<>();

  public MemoryEventStore addExamples() {
    events.add(
        new UserCreatedEvent(
            "0dc31588-bda7-4987-adc5-ad4413fc3e54",
            Instant.parse("2021-07-16T19:32:12Z"),
            "Alice"));
    return this;
  }

  @Override
  public void record(Event event) {
    events.add(event);
    notifyRecordedObservers(event);
  }

  @Override
  public Stream<Event> replay() {
    return events.stream();
  }
}
