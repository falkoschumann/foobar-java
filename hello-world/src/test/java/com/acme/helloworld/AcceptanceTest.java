/*
 * Hello World - Application
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.CsvEventStore;
import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class AcceptanceTest {
  private static final Path EVENT_STREAM_FILE = Paths.get("build/event-store/event-stream.csv");

  private static RequestHandler requestHandler;

  @BeforeAll
  static void initAll() throws Exception {
    Files.deleteIfExists(EVENT_STREAM_FILE);
    Files.createDirectories(EVENT_STREAM_FILE.getParent());
    var eventStore = new CsvEventStore(EVENT_STREAM_FILE);
    // TODO Teste gegen "richtigen" Preferences Repository
    var preferencesRepository = new MemoryPreferencesRepository();
    requestHandler = new RequestHandler(eventStore, preferencesRepository);
  }

  @Test
  @Order(1)
  void step1_InitialValues() throws Exception {
    var mainWindowBoundsQueryResult =
        requestHandler.handle(new MainWindowBoundsQuery()).get(2, TimeUnit.SECONDS);
    assertEquals(new MainWindowBoundsQueryResult(Bounds.NULL), mainWindowBoundsQueryResult);

    var preferencesQueryResult =
        requestHandler.handle(new PreferencesQuery()).get(2, TimeUnit.SECONDS);
    assertEquals(new PreferencesQueryResult("Hello $user"), preferencesQueryResult);

    var newestUserQueryResult =
        requestHandler.handle(new NewestUserQuery()).get(2, TimeUnit.SECONDS);
    assertEquals(new NewestUserQueryResult(null), newestUserQueryResult);
  }

  @Test
  @Order(2)
  void step2_ChangeMainWindowBounds() throws Exception {
    requestHandler
        .handle(new ChangeMainWindowBoundsCommand(new Bounds(40, 60, 400, 600)))
        .get(2, TimeUnit.SECONDS);

    var result = requestHandler.handle(new MainWindowBoundsQuery()).get(2, TimeUnit.SECONDS);
    assertEquals(new MainWindowBoundsQueryResult(new Bounds(40, 60, 400, 600)), result);
  }

  @Test
  @Order(3)
  void step3_ChangePreferences() throws Exception {
    var preferencesQueryResult =
        requestHandler
            .handle(new ChangePreferencesCommand("Konnichiwa $user"))
            .get(2, TimeUnit.SECONDS);

    assertEquals(new PreferencesQueryResult("Konnichiwa $user"), preferencesQueryResult);
  }

  @Test
  @Order(4)
  void step4_CreateUser_Alice() throws Exception {
    var newestUserQueryResult =
        requestHandler.handle(new CreateUserCommand("Alice")).get(2, TimeUnit.SECONDS);

    assertEquals(new NewestUserQueryResult(new User("Alice")), newestUserQueryResult);
  }

  @Test
  @Order(5)
  void step5_CreateUser_Bob() throws Exception {
    var newestUserQueryResult =
        requestHandler.handle(new CreateUserCommand("Bob")).get(2, TimeUnit.SECONDS);

    assertEquals(new NewestUserQueryResult(new User("Bob")), newestUserQueryResult);
  }

  @Test
  @Order(6)
  void step6_CreateUser_Alice() throws Exception {
    var newestUserQueryResult =
        requestHandler.handle(new CreateUserCommand("Alice")).get(2, TimeUnit.SECONDS);

    assertEquals(
        new NewestUserQueryResult(new User("Bob"), "User already exists."), newestUserQueryResult);
  }
}
