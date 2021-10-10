/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.events.UserCreatedEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CsvEventStoreTests {
  private static final Path OUT_FILE = Paths.get("build/event-store/event-stream.csv");
  private static final Path SOLL_FILE = Paths.get("src/test/resources/event-stream.csv");

  @BeforeAll
  static void setUpBeforeAll() throws Exception {
    Files.deleteIfExists(OUT_FILE);
    Files.createDirectories(OUT_FILE.getParent());
  }

  @Test
  void record() throws Exception {
    var eventStore = new CsvEventStore(OUT_FILE);
    var events = createEvents();

    eventStore.record(events.get(0));
    eventStore.record(events.get(1));

    assertEquals(Files.readAllLines(SOLL_FILE), Files.readAllLines(OUT_FILE));
  }

  @Test
  void replay_EventType() {
    var eventStore = new CsvEventStore(SOLL_FILE);

    var events = eventStore.replay(UserCreatedEvent.class);

    assertEquals(createEvents(), events.collect(Collectors.toList()));
  }

  @Test
  void replay_EventTypeCollection() {
    var eventStore = new CsvEventStore(SOLL_FILE);

    var events = eventStore.replay(List.of(UserCreatedEvent.class));

    assertEquals(createEvents(), events.collect(Collectors.toList()));
  }

  private static List<UserCreatedEvent> createEvents() {
    return List.of(
        new UserCreatedEvent(
            "a7caf1b0-886e-406f-8fbc-71da9f34714e",
            LocalDateTime.of(2021, 7, 23, 21, 54).atZone(ZoneId.systemDefault()).toInstant(),
            "Alice"),
        new UserCreatedEvent(
            "d5abc0dd-60b0-4a3b-9b2f-8b02005fb256",
            LocalDateTime.of(2021, 7, 23, 21, 57).atZone(ZoneId.systemDefault()).toInstant(),
            "Bob"));
  }
}
