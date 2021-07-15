/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;

public class PreferencesQueryHandler {
  private final PreferencesRepository repository;

  public PreferencesQueryHandler(PreferencesRepository repository) {
    this.repository = repository;
  }

  public PreferencesQueryResult handle(PreferencesQuery query) {
    var databaseConnection = repository.loadDatabaseConnection();
    return new PreferencesQueryResult(databaseConnection);
  }
}
