/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.Event;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CsvEventStore extends AbstractEventStore {
  private static final CSVFormat CSV_FORMAT = CSVFormat.RFC4180;
  private static final DateTimeFormatter TIMESTAMP_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private enum Headers {
    ID,
    Timestamp,
    Name
  }

  public CsvEventStore() {
    var path = Paths.get(System.getProperty("user.home"), ".hello-world/event-stream.csv");
    setUri(path.toString());
  }

  private Path getFile() {
    return Paths.get(getUri());
  }

  @Override
  public void record(Event event) {
    if (Files.notExists(getFile())) {
      createFile();
    }
    writeActivity((UserCreatedEvent) event);
    notifyRecordedObservers(event);
  }

  private void createFile() {
    try {
      Files.createDirectories(getFile().getParent());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    try (var out =
        Files.newBufferedWriter(
            getFile(),
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.WRITE)) {
      CSV_FORMAT.withHeader(Headers.class).print(out);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void writeActivity(UserCreatedEvent event) {
    try (var out =
        Files.newBufferedWriter(
            getFile(),
            StandardCharsets.UTF_8,
            StandardOpenOption.APPEND,
            StandardOpenOption.WRITE)) {
      var formattedTimestamp =
          LocalDateTime.ofInstant(event.timestamp(), ZoneId.systemDefault())
              .format(TIMESTAMP_FORMATTER);
      var printer = new CSVPrinter(out, CSV_FORMAT);
      printer.printRecord(event.id(), formattedTimestamp, event.name());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Override
  public Stream<? extends Event> replay() {
    try {
      var reader = Files.newBufferedReader(getFile(), StandardCharsets.UTF_8);
      var parser =
          new CSVParser(reader, CSV_FORMAT.withHeader(Headers.class).withSkipHeaderRecord());
      var iterator = parser.iterator();
      var spliterator =
          Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED | Spliterator.NONNULL);
      return StreamSupport.stream(spliterator, false)
          .map(this::createEvent)
          .onClose(() -> closeUnchecked(reader));
    } catch (NoSuchFileException e) {
      return Stream.empty();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private void closeUnchecked(Closeable closeable) {
    try {
      closeable.close();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private UserCreatedEvent createEvent(CSVRecord record) {
    var id = record.get(Headers.ID);
    var timestamp =
        LocalDateTime.parse(record.get(Headers.Timestamp), TIMESTAMP_FORMATTER)
            .atZone(ZoneId.systemDefault())
            .toInstant();
    var name = record.get(Headers.Name);
    return new UserCreatedEvent(id, timestamp, name);
  }
}
