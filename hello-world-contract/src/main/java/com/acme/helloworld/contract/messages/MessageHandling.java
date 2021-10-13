/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages;

import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;

public interface MessageHandling {
  CommandStatus handle(ChangeMainWindowBoundsCommand command);

  CommandStatus handle(ChangePreferencesCommand command);

  CommandStatus handle(CreateUserCommand command);

  MainWindowBoundsQueryResult handle(MainWindowBoundsQuery query);

  NewestUserQueryResult handle(NewestUserQuery query);

  PreferencesQueryResult handle(PreferencesQuery query);
}
