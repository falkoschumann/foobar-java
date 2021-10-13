/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.messages.Failure;
import com.acme.helloworld.contract.messages.MessageHandling;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
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

  private PreferencesController preferencesController;
  private AboutController aboutController;

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
    menuBar.setUseSystemMenuBar(true);
    preferencesController = PreferencesController.create(stage);
    preferencesController
        .getStage()
        .setOnHidden(e -> display(messageHandling.handle(new PreferencesQuery())));
    aboutController = AboutController.create(stage);

    greetingLabel.textProperty().bind(model.greetingProperty());
    userNameText.textProperty().bindBidirectional(model.userNameProperty());
    createUserButton.disableProperty().bind(model.createUserButtonDisableProperty());
  }

  public void run() {
    display(messageHandling.handle(new MainWindowBoundsQuery()));
    display(messageHandling.handle(new PreferencesQuery()));
    display(messageHandling.handle(new NewestUserQuery()));
  }

  public void display(MainWindowBoundsQueryResult result) {
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

  public void display(PreferencesQueryResult result) {
    model.updateGreetingTemplate(result.greeting());
    preferencesController.display(result);
  }

  public void display(NewestUserQueryResult result) {
    if (result.succeeded()) {
      model.updateNewestUser(result.user());
    } else {
      var alert = new Alert(AlertType.WARNING);
      alert.initOwner(stage);
      alert.setTitle("Warning");
      alert.setHeaderText("User not created");
      alert.setContentText(result.errorMessage());
      alert.show();
    }
  }

  public void display(Throwable exception) {
    exception.printStackTrace();

    var alert = new Alert(AlertType.ERROR);
    alert.initOwner(stage);
    alert.setTitle("Error");
    alert.setHeaderText("An unexpected Error occurred");
    alert.setContentText(exception.getLocalizedMessage());
    alert.show();
  }

  @FXML
  private void handleShowPreferences() {
    preferencesController.setOnChangePreferencesCommand(messageHandling::handle);
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
    aboutController.run();
  }

  @FXML
  private void handleCreateUser() {
    if (userNameText.isDisabled()) {
      return;
    }

    var status = messageHandling.handle(new CreateUserCommand(userNameText.getText()));
    var errorMessage = (status instanceof Failure f) ? f.errorMessage() : null;
    var result = messageHandling.handle(new NewestUserQuery());
    display(new NewestUserQueryResult(result.user(), errorMessage));
  }
}
