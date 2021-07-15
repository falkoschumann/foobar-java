package com.acme.helloworld.frontend;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Stages {
  private Stages() {}

  public static void setDefaultDialogBehavior(Stage stage) {
    stage.addEventHandler(
        KeyEvent.KEY_RELEASED,
        e -> {
          if (e.isShiftDown() && KeyCode.W == e.getCode()) {
            stage.hide();
          }
        });
  }
}
