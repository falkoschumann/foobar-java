/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;

public class ChangeMainWindowBoundsCommandHandler {
  private final PreferencesRepository preferencesRepository;

  public ChangeMainWindowBoundsCommandHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public CommandStatus handle(ChangeMainWindowBoundsCommand command) {
    try {
      preferencesRepository.storeMainWindowBounds(command.bounds());
      return new Success();
    } catch (Exception e) {
      return new Failure("Storing settings failed: " + e.getMessage());
    }
  }
}
