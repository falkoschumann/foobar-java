/*
 * Hello World - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend;

import de.muspellheim.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import de.muspellheim.helloworld.backend.messagehandlers.UserQueryHandler;
import de.muspellheim.helloworld.contract.MessageHandling;
import de.muspellheim.helloworld.contract.messages.commands.CommandStatus;
import de.muspellheim.helloworld.contract.messages.commands.CreateUserCommand;
import de.muspellheim.helloworld.contract.messages.queries.UserQuery;
import de.muspellheim.helloworld.contract.messages.queries.UserQueryResult;

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
