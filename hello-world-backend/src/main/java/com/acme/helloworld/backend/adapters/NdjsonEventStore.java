/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.Event;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class NdjsonEventStore extends AbstractEventStore {
  private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ISO_INSTANT;

  private final Path file;

  public NdjsonEventStore() {
    this(Paths.get(System.getProperty("user.home"), ".hello-world", "event-stream.ndjson"));
  }

  public NdjsonEventStore(Path file) {
    this.file = file;
  }

  @Override
  public void record(Event event) {
    if (Files.notExists(file)) {
      createFile();
    }
    write(event);
    notifyRecordedObservers(event);
  }

  private void createFile() {
    try {
      Files.createDirectories(file.getParent());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void write(Event event) {
    try {
      JsonObject json = createJson(event);
      Files.writeString(
          file,
          json + "\n",
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE,
          StandardOpenOption.APPEND);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private JsonObject createJson(Event event) {
    JsonObject json;
    if (event instanceof UserCreatedEvent e) {
      json = createUserCreatedEvent(e);
    } else {
      throw new IllegalStateException("Unknown event type: " + event.getClass());
    }
    return json;
  }

  private JsonObject createUserCreatedEvent(UserCreatedEvent event) {
    JsonObject json = createEvent(event);
    json.addProperty("name", event.name());
    return json;
  }

  private JsonObject createEvent(Event event) {
    var json = new JsonObject();
    json.addProperty("$type", event.getClass().getSimpleName());
    json.addProperty("id", event.id());
    json.addProperty("timestamp", event.timestamp().toString());
    return json;
  }

  @Override
  public Stream<? extends Event> replay() {
    try {
      return Files.lines(file, StandardCharsets.UTF_8).map(this::createEvent);
    } catch (NoSuchFileException e) {
      return Stream.empty();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private Event createEvent(String s) {
    var json = JsonParser.parseString(s).getAsJsonObject();
    var type = json.get("$type").getAsString();
    switch (type) {
      case "UserCreatedEvent":
        return createUserCreatedEvent(json);
      default:
        throw new IllegalStateException("Unknown event type: " + type);
    }
  }

  private UserCreatedEvent createUserCreatedEvent(JsonObject json) {
    var id = json.get("id").getAsString();
    var s = json.get("timestamp").getAsString();
    var timestamp = Instant.parse(s);
    var name = json.get("name").getAsString();
    return new UserCreatedEvent(id, timestamp, name);
  }
}
