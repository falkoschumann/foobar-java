/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.messages.MessageHandling;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PreferencesController {
  @FXML private Stage stage;
  @FXML private TextField greeting;
  @FXML private Label greetingWarning;

  private final PreferencesModel model = new PreferencesModel();
  private MessageHandling messageHandling;

  public static PreferencesController create(Stage owner, MessageHandling messageHandling) {
    try {
      var location = PreferencesController.class.getResource("PreferencesView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.load();

      var controller = (PreferencesController) loader.getController();
      controller.stage.initOwner(owner);
      controller.messageHandling = messageHandling;
      return controller;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @FXML
  private void initialize() {
    model.initGreetingWarningStyleClass(greetingWarning.getStyleClass());

    greeting.textProperty().bindBidirectional(model.greetingProperty());
    greeting
        .textProperty()
        .addListener(
            it -> messageHandling.handle(new ChangePreferencesCommand(greeting.getText())));
    greetingWarning.textProperty().bind(model.greetingWarningProperty());
    Stages.hookWindowCloseHandler(stage, this::handleClose);
  }

  public void run() {
    display(messageHandling.handle(new PreferencesQuery()));
    stage.show();
  }

  private void display(PreferencesQueryResult result) {
    greeting.setText(result.greeting());
  }

  @FXML
  private void handleClose() {
    stage.close();
  }
}
