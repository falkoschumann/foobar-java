/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.EventStore;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;

public class CreateUserCommandHandler {
  // TODO Nutze Replay nur initial und dann Recorded Observer
  private final EventStore eventStore;

  public CreateUserCommandHandler(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public CommandStatus handle(CreateUserCommand command) {
    var users = eventStore.replay(UserCreatedEvent.class).map(UserCreatedEvent::name).toList();
    if (users.contains(command.name())) {
      return new Failure("User already exists");
    }

    eventStore.record(new UserCreatedEvent(command.name()));
    return new Success();
  }
}
