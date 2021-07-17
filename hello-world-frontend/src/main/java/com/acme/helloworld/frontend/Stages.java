/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Stages {
  private Stages() {}

  public static void hookCloseHandler(Stage stage) {
    hookCloseHandler(stage, stage::close);
  }

  public static void hookCloseHandler(Stage stage, Runnable handler) {
    stage.addEventHandler(
        KeyEvent.KEY_RELEASED,
        e -> {
          if (e.isShortcutDown() && KeyCode.W == e.getCode()) {
            e.consume();
            handler.run();
          }
        });
  }
}
