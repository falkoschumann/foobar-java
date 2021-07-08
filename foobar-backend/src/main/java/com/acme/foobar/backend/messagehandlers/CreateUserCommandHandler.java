/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.messagehandlers;

import com.acme.foobar.backend.UserRepository;
import com.acme.foobar.contract.data.User;
import com.acme.foobar.contract.messages.commands.CommandStatus;
import com.acme.foobar.contract.messages.commands.CreateUserCommand;
import com.acme.foobar.contract.messages.commands.Failure;
import com.acme.foobar.contract.messages.commands.Success;

public class CreateUserCommandHandler {
  private final UserRepository repository;

  public CreateUserCommandHandler(UserRepository repository) {
    this.repository = repository;
  }

  public CommandStatus handle(CreateUserCommand command) {
    try {
      repository.setCurrentUser(new User(command.name()));
      return new Success();
    } catch (Exception e) {
      return new Failure(e.getLocalizedMessage());
    }
  }
}
