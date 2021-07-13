/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.WindowBounds;
import java.util.prefs.Preferences;

public class JavaPreferencesRepository implements PreferencesRepository {
  private static final String WINDOW_BOUNDS_X = "windowBounds/x";
  private static final String WINDOW_BOUNDS_Y = "windowBounds/y";
  private static final String WINDOW_BOUNDS_WIDTH = "windowBounds/width";
  private static final String WINDOW_BOUNDS_HEIGHT = "windowBounds/height";

  @Override
  public WindowBounds loadWindowBounds() {
    var prefs = Preferences.userRoot();
    var x = prefs.getDouble(WINDOW_BOUNDS_X, 0);
    var y = prefs.getDouble(WINDOW_BOUNDS_Y, 0);
    var width = prefs.getDouble(WINDOW_BOUNDS_WIDTH, 0);
    var height = prefs.getDouble(WINDOW_BOUNDS_HEIGHT, 0);
    return new WindowBounds(x, y, width, height);
  }

  @Override
  public void storeWindowBounds(WindowBounds bounds) {
    var prefs = Preferences.userRoot();
    prefs.putDouble(WINDOW_BOUNDS_X, bounds.x());
    prefs.putDouble(WINDOW_BOUNDS_Y, bounds.y());
    prefs.putDouble(WINDOW_BOUNDS_WIDTH, bounds.width());
    prefs.putDouble(WINDOW_BOUNDS_HEIGHT, bounds.height());
  }
}
