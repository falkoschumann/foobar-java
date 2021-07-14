/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.data.Preferences;

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
  public Preferences loadPreferences() {
    var useDefaults = preferences.getBoolean(DATABASE_USE_DEFAULTS, true);
    var host = preferences.get(DATABASE_HOST, "");
    var port = preferences.getInt(DATABASE_PORT, 0);
    var user = preferences.get(DATABASE_USER, "");
    var password = preferences.get(DATABASE_PASSWORD, "");
    var database = preferences.get(DATABASE_DATABASE, "");
    return new Preferences(
        new DatabaseConnection(useDefaults, host, port, user, password, database));
  }

  @Override
  public void storePreferences(Preferences preferences) {
    this.preferences.putBoolean(
        DATABASE_USE_DEFAULTS, preferences.databaseConnection().useDefaults());
    this.preferences.put(DATABASE_HOST, preferences.databaseConnection().host());
    this.preferences.putInt(DATABASE_PORT, preferences.databaseConnection().port());
    this.preferences.put(DATABASE_USER, preferences.databaseConnection().user());
    this.preferences.put(DATABASE_PASSWORD, preferences.databaseConnection().password());
    this.preferences.put(DATABASE_DATABASE, preferences.databaseConnection().database());
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
  public void storeWindowBounds(WindowBounds bounds) {
    preferences.putDouble(WINDOW_BOUNDS_X, bounds.x());
    preferences.putDouble(WINDOW_BOUNDS_Y, bounds.y());
    preferences.putDouble(WINDOW_BOUNDS_WIDTH, bounds.width());
    preferences.putDouble(WINDOW_BOUNDS_HEIGHT, bounds.height());
  }
}
