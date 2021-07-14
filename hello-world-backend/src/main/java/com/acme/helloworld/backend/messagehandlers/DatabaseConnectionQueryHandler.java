/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.queries.DatabaseConnectionQuery;
import com.acme.helloworld.contract.messages.queries.DatabaseConnectionQueryResult;

public class DatabaseConnectionQueryHandler {
  private final PreferencesRepository repository;

  public DatabaseConnectionQueryHandler(PreferencesRepository repository) {
    this.repository = repository;
  }

  public DatabaseConnectionQueryResult handle(DatabaseConnectionQuery query) {
    var preferences = repository.loadPreferences();
    return new DatabaseConnectionQueryResult(preferences.databaseConnection());
  }
}
