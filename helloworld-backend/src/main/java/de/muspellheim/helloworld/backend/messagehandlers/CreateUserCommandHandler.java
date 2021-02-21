/*
 * Hello World - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.messagehandlers;

import de.muspellheim.helloworld.backend.UserRepository;
import de.muspellheim.helloworld.contract.data.User;
import de.muspellheim.helloworld.contract.messages.commands.CommandStatus;
import de.muspellheim.helloworld.contract.messages.commands.CreateUserCommand;
import de.muspellheim.helloworld.contract.messages.commands.Failure;
import de.muspellheim.helloworld.contract.messages.commands.Success;

public class CreateUserCommandHandler {
  private final UserRepository repository;

  public CreateUserCommandHandler(UserRepository repository) {
    this.repository = repository;
  }

  public CommandStatus handle(CreateUserCommand command) {
    try {
      repository.setCurrentUser(new User(command.getName()));
      return new Success();
    } catch (Exception e) {
      return new Failure(e.getLocalizedMessage());
    }
  }
}
