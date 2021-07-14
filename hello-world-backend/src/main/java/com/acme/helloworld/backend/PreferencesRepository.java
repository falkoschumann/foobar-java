/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

import com.acme.helloworld.contract.data.Preferences;

public interface PreferencesRepository {
  Preferences loadPreferences();

  void storePreferences(Preferences preferences);

  WindowBounds loadWindowBounds();

  void storeWindowBounds(WindowBounds bounds);

  record WindowBounds(double x, double y, double width, double height) {
    public static final WindowBounds NULL = new WindowBounds(0, 0, 0, 0);
  }
}
