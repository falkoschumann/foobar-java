/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;

public class ChangePreferencesCommandHandler {
  private final PreferencesRepository repository;

  public ChangePreferencesCommandHandler(PreferencesRepository repository) {
    this.repository = repository;
  }

  public CommandStatus handle(ChangePreferencesCommand command) {
    try {
      repository.storeGreeting(command.greeting());
      return new Success();
    } catch (Exception e) {
      return new Failure("Storing settings failed: " + e.getMessage());
    }
  }
}
