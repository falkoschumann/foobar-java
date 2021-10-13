/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.CommandStatus;
import com.acme.helloworld.contract.messages.Success;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;

public class ChangeMainWindowBoundsCommandHandler {
  private final PreferencesRepository preferencesRepository;

  public ChangeMainWindowBoundsCommandHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public CommandStatus handle(ChangeMainWindowBoundsCommand command) {
    preferencesRepository.storeMainWindowBounds(command.bounds());
    return new Success();
  }
}
