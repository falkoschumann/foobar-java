/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class ChangePreferencesCommandHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new ChangePreferencesCommandHandler(repository);

    var status =
        handler.handle(
            new ChangePreferencesCommand(
                new DatabaseConnection(false, "postgres", 5432, "acme", "acme", "acme")));

    assertAll(
        () -> assertEquals(new Success(), status),
        () ->
            assertEquals(
                new DatabaseConnection(false, "postgres", 5432, "acme", "acme", "acme"),
                repository.loadDatabaseConnection()));
  }
}
