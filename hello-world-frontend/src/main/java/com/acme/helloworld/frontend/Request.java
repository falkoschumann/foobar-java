/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import java.util.concurrent.CompletableFuture;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public abstract class Request extends Task<Void> {
  public static void runAsync(Runnable handler) {
    runAsync(
        new Request() {
          @Override
          protected Void call() {
            handler.run();
            return null;
          }
        });
  }

  public static void runAsync(Request request) {
    CompletableFuture.runAsync(request);
  }

  @Override
  protected void failed() {
    getException().printStackTrace();

    var alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An unexpected Error occurred");
    alert.setContentText(getException().getMessage());
    alert.show();
  }
}
