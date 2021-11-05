/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.MessageHandling;
import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWindowController {
  @FXML private Stage stage;
  @FXML private MenuBar menuBar;
  @FXML private Label greetingLabel;
  @FXML private TextField userNameText;
  @FXML private Button createUserButton;

  private final MainWindowModel model = new MainWindowModel();
  private MessageHandling messageHandling;

  public static MainWindowController create(Stage stage, MessageHandling messageHandling) {
    try {
      var location = MainWindowController.class.getResource("MainWindowView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.setRoot(stage);
      loader.load();

      var controller = (MainWindowController) loader.getController();
      controller.messageHandling = messageHandling;
      return controller;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @FXML
  private void initialize() {
    // Build
    menuBar.setUseSystemMenuBar(true);

    // Bind
    greetingLabel.textProperty().bind(model.greetingProperty());
    userNameText.textProperty().bindBidirectional(model.userNameProperty());
    createUserButton.disableProperty().bind(model.createUserButtonDisableProperty());
  }

  public void run() {
    display(messageHandling.handle(new MainWindowBoundsQuery()));
    Request.runAsync(() -> messageHandling.handle(new NewestUserQuery()), this::display);
  }

  @FXML
  private void handleShowPreferences() {
    var preferencesController = PreferencesController.create(stage, messageHandling);
    preferencesController.run();
  }

  @FXML
  private void handleClose() {
    messageHandling.handle(
        new ChangeMainWindowBoundsCommand(
            new Bounds(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight())));
    stage.close();
  }

  @FXML
  private void handleShowAbout() {
    var controller = AboutController.create(stage);
    controller.run();
  }

  @FXML
  private void handleCreateUser() {
    if (model.isCreateUserButtonDisable()) {
      return;
    }

    Request.runAsync(
        () -> {
          var status = messageHandling.handle(new CreateUserCommand(model.getUserName()));
          if (status instanceof Success) {
            var result = messageHandling.handle(new NewestUserQuery());
            Platform.runLater(() -> display(result));
          } else if (status instanceof Failure f) {
            Platform.runLater(() -> display(f));
          }
        });
  }

  private void display(MainWindowBoundsQueryResult result) {
    if (!Bounds.NULL.equals(result.bounds())) {
      var x = result.bounds().x();
      var y = result.bounds().y();
      var width = result.bounds().width();
      var height = result.bounds().height();
      if (!Screen.getScreensForRectangle(x, y, width, height).isEmpty()) {
        stage.setX(x);
        stage.setY(y);
        stage.setWidth(width);
        stage.setHeight(height);
      }
    }
    stage.show();
  }

  private void display(NewestUserQueryResult result) {
    model.setGreeting(result.greeting());
  }

  private void display(Failure failure) {
    var alert = new Alert(AlertType.WARNING);
    alert.setTitle("Failure");
    alert.setContentText(failure.errorMessage());
    alert.show();
  }
}
