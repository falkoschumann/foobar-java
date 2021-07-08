/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.messagehandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.foobar.backend.adapters.MemoryUserRepository;
import com.acme.foobar.contract.messages.commands.CreateUserCommand;
import com.acme.foobar.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class CreateUserCommandHandlerTests {
  @Test
  void handleSuccessfully() {
    var repository = new MemoryUserRepository();
    var handler = new CreateUserCommandHandler(repository);

    var status = handler.handle(new CreateUserCommand("Alice"));

    assertEquals(new Success(), status);
    assertThat(repository.getCurrentUser().id(), is(any(String.class)));
    assertEquals("Alice", repository.getCurrentUser().name());
  }
}
