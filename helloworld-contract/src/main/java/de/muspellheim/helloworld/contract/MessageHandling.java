/*
 * Hello World - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.contract;

import de.muspellheim.helloworld.contract.messages.commands.CommandStatus;
import de.muspellheim.helloworld.contract.messages.commands.CreateUserCommand;
import de.muspellheim.helloworld.contract.messages.queries.UserQuery;
import de.muspellheim.helloworld.contract.messages.queries.UserQueryResult;

public interface MessageHandling {
  CommandStatus handle(CreateUserCommand command);

  UserQueryResult handle(UserQuery query);
}
