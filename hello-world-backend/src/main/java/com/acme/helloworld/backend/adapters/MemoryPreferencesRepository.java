/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.WindowBounds;

public class MemoryPreferencesRepository implements PreferencesRepository {
  private WindowBounds windowBounds = WindowBounds.NULL;

  @Override
  public WindowBounds loadWindowBounds() {
    return windowBounds;
  }

  @Override
  public void storeWindowBounds(WindowBounds bounds) {
    this.windowBounds = bounds;
  }
}
