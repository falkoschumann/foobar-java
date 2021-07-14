/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.data.Preferences;

public class MemoryPreferencesRepository implements PreferencesRepository {
  private Preferences preferences;
  private WindowBounds windowBounds = WindowBounds.NULL;

  public void addExamples() {
    preferences =
        new Preferences(
            new DatabaseConnection(true, "localhost", 5432, "acme_test", "acme_test", "acme_test"));
    windowBounds = new WindowBounds(36, 24, 640, 480);
  }

  @Override
  public Preferences loadPreferences() {
    return preferences;
  }

  @Override
  public void storePreferences(Preferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public WindowBounds loadWindowBounds() {
    return windowBounds;
  }

  @Override
  public void storeWindowBounds(WindowBounds bounds) {
    this.windowBounds = bounds;
  }
}
