/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.contract.messages.commands.ChangeWindowBoundsCommand;
import com.acme.helloworld.contract.messages.notifications.WindowBoundsChangedNotification;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

public class WindowBoundsChangedNotificationHandler {
  @Getter @Setter private Consumer<ChangeWindowBoundsCommand> onChangeWindowBoundsCommand;

  public void handle(WindowBoundsChangedNotification notification) {
    onChangeWindowBoundsCommand.accept(new ChangeWindowBoundsCommand(notification.bounds()));
  }
}
