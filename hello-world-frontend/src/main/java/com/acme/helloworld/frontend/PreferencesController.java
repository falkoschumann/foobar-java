/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PreferencesController {
  @FXML private Stage stage;
  @FXML private CheckBox useDefaults;
  @FXML private TextField host;
  @FXML private Spinner<Integer> port;
  @FXML private TextField user;
  @FXML private PasswordField password;
  @FXML private TextField database;
  @FXML private Label testConnectionMessage;

  private PreferencesModel model;

  public static PreferencesController create(Stage owner) {
    try {
      var location = PreferencesController.class.getResource("PreferencesView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.load();

      var controller = (PreferencesController) loader.getController();
      controller.stage.initModality(Modality.APPLICATION_MODAL);
      controller.stage.initOwner(owner);
      controller.stage.initStyle(StageStyle.UTILITY);
      return controller;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @FXML
  private void initialized() {
    model = new PreferencesModel();

    Stages.setDefaultDialogBehavior(stage);
  }

  public void run() {
    stage.show();
  }

  @FXML
  private void handleTestConnection() {}

  @FXML
  private void handleCloseRequest(WindowEvent windowEvent) {}
}
