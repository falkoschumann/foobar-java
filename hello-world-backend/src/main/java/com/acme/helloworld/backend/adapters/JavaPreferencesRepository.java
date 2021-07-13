/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.WindowBounds;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class JavaPreferencesRepository implements PreferencesRepository {
  private static final String APP_NODE = "/com/acme/helloworld";
  private static final String WINDOW_BOUNDS_X = "windowBounds/x";
  private static final String WINDOW_BOUNDS_Y = "windowBounds/y";
  private static final String WINDOW_BOUNDS_WIDTH = "windowBounds/width";
  private static final String WINDOW_BOUNDS_HEIGHT = "windowBounds/height";

  private final Preferences preferences = Preferences.userRoot().node(APP_NODE);

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
    try {
      preferences.flush();
    } catch (BackingStoreException e) {
      throw new IllegalStateException(e);
    }
  }
}
