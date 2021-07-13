/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.contract.messages.notifications.WindowBoundsChangedNotification;

public class WindowBoundsChangedNotificationHandler {
  // TODO Schreibe Test
  private final PreferencesRepository preferencesRepository;

  public WindowBoundsChangedNotificationHandler(PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
  }

  public void handle(WindowBoundsChangedNotification notification) {
    // FIXME Notification muss Command erzeugen und darf nicht selber handeln!!
    preferencesRepository.storeWindowBounds(notification.bounds());
  }
}
