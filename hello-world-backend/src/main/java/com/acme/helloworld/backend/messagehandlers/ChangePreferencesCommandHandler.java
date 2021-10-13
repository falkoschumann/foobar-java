/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.CommandStatus;
import com.acme.helloworld.contract.messages.Success;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;

public class ChangePreferencesCommandHandler {
  private final PreferencesRepository repository;

  public ChangePreferencesCommandHandler(PreferencesRepository repository) {
    this.repository = repository;
  }

  public CommandStatus handle(ChangePreferencesCommand command) {
    repository.storeGreeting(command.greeting());
    return new Success();
  }
}
