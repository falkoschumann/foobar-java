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
import java.util.LinkedHashSet;
import java.util.Set;

public class CreateUserCommandHandler {
  private final EventStore eventStore;
  private final Set<String> userNames = new LinkedHashSet<>();

  public CreateUserCommandHandler(EventStore eventStore) {
    this.eventStore = eventStore;
    replay(eventStore);
    hookLisenter(eventStore);
  }

  private void replay(EventStore eventStore) {
    var names = eventStore.replay(UserCreatedEvent.class).map(UserCreatedEvent::name).toList();
    userNames.addAll(names);
  }

  private void hookLisenter(EventStore eventStore) {
    eventStore.addRecordedObserver(
        it -> {
          if (it instanceof UserCreatedEvent e) {
            userNames.add(e.name());
          }
        });
  }

  public CommandStatus handle(CreateUserCommand command) {
    if (userNames.contains(command.name())) {
      return new Failure("User already exists.");
    }

    eventStore.record(new UserCreatedEvent(command.name()));
    return new Success();
  }
}
