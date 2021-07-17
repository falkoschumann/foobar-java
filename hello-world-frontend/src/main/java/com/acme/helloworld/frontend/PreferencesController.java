/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.Setter;

public class PreferencesController {
  @Getter @Setter private Consumer<ChangePreferencesCommand> onChangePreferencesCommand;

  @FXML private Stage stage;
  @FXML private TextField greeting;

  public static PreferencesController create(Stage owner) {
    try {
      var location = PreferencesController.class.getResource("PreferencesView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.load();

      var controller = (PreferencesController) loader.getController();
      controller.stage.initModality(Modality.APPLICATION_MODAL);
      controller.stage.initOwner(owner);
      return controller;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @FXML
  private void initialize() {
    // FIXME Ctrl-W closes main window too
    Stages.hookCloseHandler(stage, () -> handleCloseRequest(null));
  }

  public void run() {
    stage.show();
  }

  public void display(PreferencesQueryResult result) {
    greeting.setText(result.greeting());
  }

  @FXML
  private void handleCloseRequest(WindowEvent windowEvent) {
    onChangePreferencesCommand.accept(new ChangePreferencesCommand(greeting.getText()));
  }
}
