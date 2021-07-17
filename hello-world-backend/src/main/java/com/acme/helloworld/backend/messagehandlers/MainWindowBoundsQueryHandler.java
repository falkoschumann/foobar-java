/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;

public class MainWindowBoundsQueryHandler {
  private final PreferencesRepository preferencesRepository;

  public MainWindowBoundsQueryHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public MainWindowBoundsQueryResult handle(MainWindowBoundsQuery query) {
    var windowBounds = preferencesRepository.loadMainWindowBounds();
    return new MainWindowBoundsQueryResult(windowBounds);
  }
}
