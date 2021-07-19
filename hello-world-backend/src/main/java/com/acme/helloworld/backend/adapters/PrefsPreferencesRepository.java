/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;
import java.util.prefs.Preferences;

public class PrefsPreferencesRepository implements PreferencesRepository {
  private static final String APP_NODE = "/com/acme/helloworld";
  private static final String GREETING = "greeting";
  private static final String MAIN_WINDOW_BOUNDS_X = "mainWindowBounds/x";
  private static final String MAIN_WINDOW_BOUNDS_Y = "mainWindowBounds/y";
  private static final String MAIN_WINDOW_BOUNDS_WIDTH = "mainWindowBounds/width";
  private static final String MAIN_WINDOW_BOUNDS_HEIGHT = "mainWindowBounds/height";

  private final Preferences preferences = Preferences.userRoot().node(APP_NODE);

  @Override
  public String loadGreeting() {
    return preferences.get(GREETING, "Hello $user");
  }

  @Override
  public void storeGreeting(String greeting) {
    preferences.put(GREETING, greeting);
  }

  @Override
  public Bounds loadMainWindowBounds() {
    var x = preferences.getDouble(MAIN_WINDOW_BOUNDS_X, 0);
    var y = preferences.getDouble(MAIN_WINDOW_BOUNDS_Y, 0);
    var width = preferences.getDouble(MAIN_WINDOW_BOUNDS_WIDTH, 0);
    var height = preferences.getDouble(MAIN_WINDOW_BOUNDS_HEIGHT, 0);
    return new Bounds(x, y, width, height);
  }

  @Override
  public void storeMainWindowBounds(Bounds bounds) {
    preferences.putDouble(MAIN_WINDOW_BOUNDS_X, bounds.x());
    preferences.putDouble(MAIN_WINDOW_BOUNDS_Y, bounds.y());
    preferences.putDouble(MAIN_WINDOW_BOUNDS_WIDTH, bounds.width());
    preferences.putDouble(MAIN_WINDOW_BOUNDS_HEIGHT, bounds.height());
  }
}
