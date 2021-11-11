/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class ChangePreferencesCommandHandlerTest {
  @Test
  void handle_success() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new ChangePreferencesCommandHandler(repository);

    var status = handler.handle(new ChangePreferencesCommand("Bonjour $user"));

    assertEquals(new Success(), status, "command status");
    assertEquals("Bonjour $user", repository.loadGreeting(), "greeting");
  }
}
