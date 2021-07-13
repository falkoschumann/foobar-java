/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.backend.PreferencesRepository.WindowBounds;
import com.acme.helloworld.contract.messages.commands.ChangeWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.Success;

public class ChangeWindowBoundsCommandHandler {
  private final PreferencesRepository preferencesRepository;

  public ChangeWindowBoundsCommandHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public CommandStatus handle(ChangeWindowBoundsCommand command) {
    preferencesRepository.storeWindowBounds(
        new WindowBounds(command.x(), command.y(), command.width(), command.height()));
    return new Success();
  }
}
