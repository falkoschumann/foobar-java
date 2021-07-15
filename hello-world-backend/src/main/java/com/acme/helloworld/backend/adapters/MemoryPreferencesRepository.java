/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.data.WindowBounds;

public class MemoryPreferencesRepository implements PreferencesRepository {
  private DatabaseConnection databaseConnection;
  private WindowBounds windowBounds = WindowBounds.NULL;

  public void addExamples() {
    databaseConnection =
        new DatabaseConnection(true, "localhost", 5432, "acme_test", "acme_test", "acme_test");
    windowBounds = new WindowBounds(36, 24, 640, 480);
  }

  @Override
  public DatabaseConnection loadDatabaseConnection() {
    return databaseConnection;
  }

  @Override
  public void storeDatabaseConnection(DatabaseConnection preferences) {
    this.databaseConnection = preferences;
  }

  @Override
  public WindowBounds loadWindowBounds() {
    return windowBounds;
  }

  @Override
  public void storeWindowBounds(WindowBounds windowBounds) {
    this.windowBounds = windowBounds;
  }
}
