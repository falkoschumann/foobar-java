/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

import com.acme.helloworld.backend.messagehandlers.ChangeMainWindowBoundsCommandHandler;
import com.acme.helloworld.backend.messagehandlers.ChangePreferencesCommandHandler;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.MainWindowBoundsQueryHandler;
import com.acme.helloworld.backend.messagehandlers.NewestUserQueryHandler;
import com.acme.helloworld.backend.messagehandlers.PreferencesQueryHandler;
import com.acme.helloworld.contract.MessageHandling;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;

public class MessageHandler implements MessageHandling {
  private final ChangeMainWindowBoundsCommandHandler changeMainWindowBoundsCommandHandler;
  private final ChangePreferencesCommandHandler changePreferencesCommandHandler;
  private final CreateUserCommandHandler createUserCommandHandler;
  private final MainWindowBoundsQueryHandler mainWindowBoundsQueryHandler;
  private final NewestUserQueryHandler newestUserQueryHandler;
  private final PreferencesQueryHandler preferencesQueryHandler;

  public MessageHandler(EventStore eventStore, PreferencesRepository preferencesRepository) {
    changeMainWindowBoundsCommandHandler =
        new ChangeMainWindowBoundsCommandHandler(preferencesRepository);
    changePreferencesCommandHandler = new ChangePreferencesCommandHandler(preferencesRepository);
    createUserCommandHandler = new CreateUserCommandHandler(eventStore);
    mainWindowBoundsQueryHandler = new MainWindowBoundsQueryHandler(preferencesRepository);
    newestUserQueryHandler = new NewestUserQueryHandler(eventStore, preferencesRepository);
    preferencesQueryHandler = new PreferencesQueryHandler(preferencesRepository);
  }

  @Override
  public CommandStatus handle(ChangeMainWindowBoundsCommand command) {
    return changeMainWindowBoundsCommandHandler.handle(command);
  }

  @Override
  public CommandStatus handle(ChangePreferencesCommand command) {
    return changePreferencesCommandHandler.handle(command);
  }

  @Override
  public CommandStatus handle(CreateUserCommand command) {
    return createUserCommandHandler.handle(command);
  }

  @Override
  public MainWindowBoundsQueryResult handle(MainWindowBoundsQuery query) {
    return mainWindowBoundsQueryHandler.handle(query);
  }

  @Override
  public NewestUserQueryResult handle(NewestUserQuery query) {
    return newestUserQueryHandler.handle(query);
  }

  @Override
  public PreferencesQueryResult handle(PreferencesQuery query) {
    return preferencesQueryHandler.handle(query);
  }
}
