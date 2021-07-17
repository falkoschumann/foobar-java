/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryEventStore;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class CreateUserCommandHandlerTests {
  @Test
  void testHandle() {
    var eventStore = new MemoryEventStore();
    eventStore.addExamples();
    var handler = new CreateUserCommandHandler(eventStore);

    var status = handler.handle(new CreateUserCommand("Bob"));

    assertEquals(new Success(), status, "command status");
    assertEquals(2, eventStore.replay().count(), "1 new event");
    var event = eventStore.replay().toList().get(1);
    assertThat("event.id", event.id(), is(any(String.class)));
    assertThat("event.timestamp", event.timestamp(), is(any(Instant.class)));
    assertEquals("Bob", ((UserCreatedEvent) event).name(), "event.name");
  }

  @Test
  void testHandle_UserAlreadyExists() {
    var eventStore = new MemoryEventStore();
    eventStore.addExamples();
    var handler = new CreateUserCommandHandler(eventStore);

    var status = handler.handle(new CreateUserCommand("Alice"));

    assertEquals(new Failure("User already exists"), status, "command status");
    assertEquals(1, eventStore.replay().count(), "no new event");
  }
}
