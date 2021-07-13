/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.WindowBounds;
import com.acme.helloworld.contract.messages.commands.ChangeWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class ChangeWindowBoundsCommandHandlerTests {
  @Test
  void handle_Memory() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new ChangeWindowBoundsCommandHandler(repository);

    var status = handler.handle(new ChangeWindowBoundsCommand(new WindowBounds(1, 2, 3, 4)));

    assertEquals(new Success(), status);
    assertEquals(new WindowBounds(1, 2, 3, 4), repository.loadWindowBounds());
  }
}
