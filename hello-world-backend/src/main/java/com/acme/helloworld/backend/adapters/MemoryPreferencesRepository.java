/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;

public class MemoryPreferencesRepository implements PreferencesRepository {
  private String greeting = "Hello $user";
  ;
  private Bounds mainWindowBounds = Bounds.NULL;

  public MemoryPreferencesRepository addExamples() {
    greeting = "Konnichiwa $user";
    mainWindowBounds = new Bounds(36, 24, 640, 480);
    return this;
  }

  @Override
  public String loadGreeting() {
    return greeting;
  }

  @Override
  public void storeGreeting(String greeting) {
    this.greeting = greeting;
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
