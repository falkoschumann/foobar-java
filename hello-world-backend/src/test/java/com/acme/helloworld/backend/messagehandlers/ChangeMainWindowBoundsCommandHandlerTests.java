/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class ChangeMainWindowBoundsCommandHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new ChangeMainWindowBoundsCommandHandler(repository);

    var status = handler.handle(new ChangeMainWindowBoundsCommand(new Bounds(1, 2, 3, 4)));

    assertAll(
        () -> assertEquals(new Success(), status),
        () -> assertEquals(new Bounds(1, 2, 3, 4), repository.loadWindowBounds()));
  }
}
