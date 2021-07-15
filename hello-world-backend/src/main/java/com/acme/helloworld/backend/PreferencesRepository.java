/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.data.WindowBounds;

public interface PreferencesRepository {
  DatabaseConnection loadDatabaseConnection();

  void storeDatabaseConnection(DatabaseConnection databaseConnection);

  WindowBounds loadWindowBounds();

  void storeWindowBounds(WindowBounds windowBounds);
}
