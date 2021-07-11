/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import org.junit.jupiter.api.Test;

class CreateUserCommandHandlerTests {
  @Test
  void handleSuccessfully() {
    var repository = new MemoryUserRepository();
    var handler = new CreateUserCommandHandler(repository);

    var status = handler.handle(new CreateUserCommand("Alice"));

    assertEquals(new Success(), status);
    var user = repository.findAll().get(0);
    assertThat(user.id(), is(any(String.class)));
    assertEquals("Alice", user.name());
  }
}
