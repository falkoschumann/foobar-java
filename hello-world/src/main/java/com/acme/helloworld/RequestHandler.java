/*
 * Hello World - Application
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import com.acme.helloworld.backend.EventStore;
import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.backend.messagehandlers.ChangeMainWindowBoundsCommandHandler;
import com.acme.helloworld.backend.messagehandlers.ChangePreferencesCommandHandler;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.MainWindowBoundsQueryHandler;
import com.acme.helloworld.backend.messagehandlers.NewestUserQueryHandler;
import com.acme.helloworld.backend.messagehandlers.PreferencesQueryHandler;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;

class RequestHandler {
  private final ChangeMainWindowBoundsCommandHandler changeMainWindowBoundsCommandHandler;
  private final ChangePreferencesCommandHandler changePreferencesCommandHandler;
  private final CreateUserCommandHandler createUserCommandHandler;

  private final MainWindowBoundsQueryHandler mainWindowBoundsQueryHandler;
  private final NewestUserQueryHandler newestUserQueryHandler;
  private final PreferencesQueryHandler preferencesQueryHandler;

  RequestHandler(EventStore eventStore, PreferencesRepository preferencesRepository) {
    changeMainWindowBoundsCommandHandler =
        new ChangeMainWindowBoundsCommandHandler(preferencesRepository);
    changePreferencesCommandHandler = new ChangePreferencesCommandHandler(preferencesRepository);
    createUserCommandHandler = new CreateUserCommandHandler(eventStore);

    mainWindowBoundsQueryHandler = new MainWindowBoundsQueryHandler(preferencesRepository);
    newestUserQueryHandler = new NewestUserQueryHandler(eventStore);
    preferencesQueryHandler = new PreferencesQueryHandler(preferencesRepository);
  }

  void handle(ChangeMainWindowBoundsCommand command) {
    changeMainWindowBoundsCommandHandler.handle(command);
  }

  PreferencesQueryResult handle(ChangePreferencesCommand command) {
    changePreferencesCommandHandler.handle(command);
    return preferencesQueryHandler.handle(new PreferencesQuery());
  }

  NewestUserQueryResult handle(CreateUserCommand command) {
    var status = createUserCommandHandler.handle(command);
    var errorMessage = (status instanceof Failure f) ? f.errorMessage() : null;
    var result = newestUserQueryHandler.handle(new NewestUserQuery());
    return new NewestUserQueryResult(result.user(), errorMessage);
  }

  MainWindowBoundsQueryResult handle(MainWindowBoundsQuery query) {
    return mainWindowBoundsQueryHandler.handle(query);
  }

  PreferencesQueryResult handle(PreferencesQuery query) {
    return preferencesQueryHandler.handle(query);
  }

  NewestUserQueryResult handle(NewestUserQuery query) {
    return newestUserQueryHandler.handle(query);
  }
}
