/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.data.WindowBounds;

public class JavaPreferencesRepository implements PreferencesRepository {
  private static final String APP_NODE = "/com/acme/helloworld";
  private static final String DATABASE_USE_DEFAULTS = "useDefaults";
  private static final String DATABASE_HOST = "host";
  private static final String DATABASE_PORT = "port";
  private static final String DATABASE_USER = "user";
  private static final String DATABASE_PASSWORD = "password";
  private static final String DATABASE_DATABASE = "database";
  private static final String WINDOW_BOUNDS_X = "windowBounds/x";
  private static final String WINDOW_BOUNDS_Y = "windowBounds/y";
  private static final String WINDOW_BOUNDS_WIDTH = "windowBounds/width";
  private static final String WINDOW_BOUNDS_HEIGHT = "windowBounds/height";

  private final java.util.prefs.Preferences preferences =
      java.util.prefs.Preferences.userRoot().node(APP_NODE);

  @Override
  public DatabaseConnection loadDatabaseConnection() {
    var useDefaults = preferences.getBoolean(DATABASE_USE_DEFAULTS, true);
    var host = preferences.get(DATABASE_HOST, "");
    var port = preferences.getInt(DATABASE_PORT, 0);
    var user = preferences.get(DATABASE_USER, "");
    var password = preferences.get(DATABASE_PASSWORD, "");
    var database = preferences.get(DATABASE_DATABASE, "");
    return         new DatabaseConnection(useDefaults, host, port, user, password, database);
  }

  @Override
  public void storeDatabaseConnection(DatabaseConnection databaseConnection) {
    this.preferences.putBoolean(DATABASE_USE_DEFAULTS, databaseConnection.useDefaults());
    this.preferences.put(DATABASE_HOST, databaseConnection.host());
    this.preferences.putInt(DATABASE_PORT, databaseConnection.port());
    this.preferences.put(DATABASE_USER, databaseConnection.user());
    this.preferences.put(DATABASE_PASSWORD, databaseConnection.password());
    this.preferences.put(DATABASE_DATABASE, databaseConnection.database());
  }

  @Override
  public WindowBounds loadWindowBounds() {
    var x = preferences.getDouble(WINDOW_BOUNDS_X, 0);
    var y = preferences.getDouble(WINDOW_BOUNDS_Y, 0);
    var width = preferences.getDouble(WINDOW_BOUNDS_WIDTH, 0);
    var height = preferences.getDouble(WINDOW_BOUNDS_HEIGHT, 0);
    return new WindowBounds(x, y, width, height);
  }

  @Override
  public void storeWindowBounds(WindowBounds windowBounds) {
    preferences.putDouble(WINDOW_BOUNDS_X, windowBounds.x());
    preferences.putDouble(WINDOW_BOUNDS_Y, windowBounds.y());
    preferences.putDouble(WINDOW_BOUNDS_WIDTH, windowBounds.width());
    preferences.putDouble(WINDOW_BOUNDS_HEIGHT, windowBounds.height());
  }
}
