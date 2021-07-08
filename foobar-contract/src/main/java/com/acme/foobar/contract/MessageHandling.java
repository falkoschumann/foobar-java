/*
 * Foobar - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.contract;

import com.acme.foobar.contract.messages.commands.CommandStatus;
import com.acme.foobar.contract.messages.commands.CreateUserCommand;
import com.acme.foobar.contract.messages.queries.UserQuery;
import com.acme.foobar.contract.messages.queries.UserQueryResult;

public interface MessageHandling {
  // TODO Entferne MessageHandling
  CommandStatus handle(CreateUserCommand command);

  UserQueryResult handle(UserQuery query);
}
