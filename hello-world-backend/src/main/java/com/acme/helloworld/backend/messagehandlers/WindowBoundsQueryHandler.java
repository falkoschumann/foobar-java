/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.queries.WindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.WindowBoundsQueryResult;

public class WindowBoundsQueryHandler {
  private final PreferencesRepository preferencesRepository;

  public WindowBoundsQueryHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public WindowBoundsQueryResult handle(WindowBoundsQuery query) {
    var bounds = preferencesRepository.loadWindowBounds();
    return new WindowBoundsQueryResult(bounds.x(), bounds.y(), bounds.width(), bounds.height());
  }
}
