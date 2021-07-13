/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.contract.data.WindowBounds;
import com.acme.helloworld.contract.messages.commands.ChangeWindowBoundsCommand;
import com.acme.helloworld.contract.messages.notifications.WindowBoundsChangedNotification;
import org.junit.jupiter.api.Test;

class WindowBoundsChangedNotificationHandlerTests {
  private ChangeWindowBoundsCommand command;

  @Test
  void testHandle() {
    var handler = new WindowBoundsChangedNotificationHandler();
    handler.setOnChangeWindowBoundsCommand(c -> command = c);

    handler.handle(new WindowBoundsChangedNotification(new WindowBounds(36, 24, 640, 480)));

    assertEquals(new ChangeWindowBoundsCommand(new WindowBounds(36, 24, 640, 480)), command);
  }
}
