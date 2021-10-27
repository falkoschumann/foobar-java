/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

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
import java.util.function.Function;
import java.util.logging.Level;
import lombok.extern.java.Log;

@Log
public class LoggingMessageHandler implements MessageHandling {
  private final MessageHandling handler;

  public LoggingMessageHandler(MessageHandling handler) {
    this.handler = handler;
  }

  @Override
  public CommandStatus handle(ChangeMainWindowBoundsCommand command) {
    return handle(handler::handle, command);
  }

  @Override
  public CommandStatus handle(ChangePreferencesCommand command) {
    return handle(handler::handle, command);
  }

  @Override
  public CommandStatus handle(CreateUserCommand command) {
    return handle(handler::handle, command);
  }

  @Override
  public MainWindowBoundsQueryResult handle(MainWindowBoundsQuery query) {
    return handle(handler::handle, query);
  }

  @Override
  public NewestUserQueryResult handle(NewestUserQuery query) {
    return handle(handler::handle, query);
  }

  @Override
  public PreferencesQueryResult handle(PreferencesQuery query) {
    return handle(handler::handle, query);
  }

  private <I, O> O handle(Function<I, O> handler, I request) {
    try {
      O response = handler.apply(request);
      log.info(() -> "Request: " + request + " -> Response: " + response);
      return response;
    } catch (Exception e) {
      log.log(Level.SEVERE, e, () -> "Request: " + request + " -> Exception: " + e);
      throw e;
    }
  }
}
