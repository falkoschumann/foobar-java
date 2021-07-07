/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend;

import com.acme.foobar.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.foobar.backend.messagehandlers.UserQueryHandler;
import com.acme.foobar.contract.MessageHandling;
import com.acme.foobar.contract.messages.commands.CommandStatus;
import com.acme.foobar.contract.messages.commands.CreateUserCommand;
import com.acme.foobar.contract.messages.queries.UserQuery;
import com.acme.foobar.contract.messages.queries.UserQueryResult;

public class MessageHandler implements MessageHandling {
  private CreateUserCommandHandler createUserCommandHandler;
  private UserQueryHandler userQueryHandler;

  public MessageHandler(UserRepository userRepository) {
    createUserCommandHandler = new CreateUserCommandHandler(userRepository);
    userQueryHandler = new UserQueryHandler(userRepository);
  }

  @Override
  public CommandStatus handle(CreateUserCommand command) {
    return createUserCommandHandler.handle(command);
  }

  @Override
  public UserQueryResult handle(UserQuery query) {
    return userQueryHandler.handle(query);
  }
}
