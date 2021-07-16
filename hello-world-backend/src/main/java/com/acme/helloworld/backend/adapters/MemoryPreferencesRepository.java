/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;

public class MemoryPreferencesRepository implements PreferencesRepository {
  private String eventStreamFile;
  private Bounds mainWindowBounds = Bounds.NULL;

  public void addExamples() {
    eventStreamFile = "~/.hello-world/event-stream.csv";
    mainWindowBounds = new Bounds(36, 24, 640, 480);
  }

  @Override
  public String loadEventStreamFile() {
    return eventStreamFile;
  }

  @Override
  public void storeEventStreamFile(String file) {
    eventStreamFile = file;
  }

  @Override
  public Bounds loadMainWindowBounds() {
    return mainWindowBounds;
  }

  @Override
  public void storeMainWindowBounds(Bounds bounds) {
    this.mainWindowBounds = bounds;
  }
}
