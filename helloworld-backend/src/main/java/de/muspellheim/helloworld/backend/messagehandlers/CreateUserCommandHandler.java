/*
 * User Stories - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.messagehandlers;

import de.muspellheim.helloworld.contract.messages.commands.CommandStatus;
import de.muspellheim.helloworld.contract.messages.commands.CreateUserCommand;
import de.muspellheim.helloworld.contract.messages.commands.Success;

public class CreateUserCommandHandler {
  public CommandStatus handle(CreateUserCommand command) {
    return new Success();
  }
}
