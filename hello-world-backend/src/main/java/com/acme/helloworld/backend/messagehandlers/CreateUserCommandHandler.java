/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;

public class CreateUserCommandHandler {
  private final UserRepository repository;

  public CreateUserCommandHandler(UserRepository repository) {
    this.repository = repository;
  }

  public CommandStatus handle(CreateUserCommand command) {
    try {
      repository.createUser(new User(command.name()));
      return new Success();
    } catch (Exception e) {
      return new Failure(e.getLocalizedMessage());
    }
  }
}
