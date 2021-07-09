/*
 * Foobar - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract;

import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.UserQuery;
import com.acme.helloworld.contract.messages.queries.UserQueryResult;

public interface MessageHandling {
  // TODO Entferne MessageHandling
  CommandStatus handle(CreateUserCommand command);

  UserQueryResult handle(UserQuery query);
}
