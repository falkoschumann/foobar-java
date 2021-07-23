/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
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
import lombok.Getter;
import lombok.Setter;

public class MainWindowController {
  @Getter @Setter private Consumer<ChangeMainWindowBoundsCommand> onChangeMainWindowBoundsCommand;
  @Getter @Setter private Consumer<ChangePreferencesCommand> onChangePreferencesCommand;
  @Getter @Setter private Consumer<CreateUserCommand> onCreateUserCommand;
  @Getter @Setter private Consumer<MainWindowBoundsQuery> onMainWindowBoundsQuery;
  @Getter @Setter private Consumer<PreferencesQuery> onPreferencesQuery;
  @Getter @Setter private Consumer<NewestUserQuery> onNewestUserQuery;

  @FXML private Stage stage;
  @FXML private MenuBar menuBar;
  @FXML private Label greeting;
  @FXML private TextField name;
  @FXML private Button createUserButton;

  private PreferencesController preferencesController;
  private AboutController aboutController;

  private MainWindowModel model;

  public static MainWindowController create(Stage stage) {
    try {
      var location = MainWindowController.class.getResource("MainWindowView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.setRoot(stage);
      loader.load();
      return loader.getController();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @FXML
  private void initialize() {
    preferencesController = PreferencesController.create(stage);
    aboutController = AboutController.create(stage);
    menuBar.setUseSystemMenuBar(true);
    model = new MainWindowModel();

    greeting.textProperty().bind(model.userGreetingProperty());
    name.textProperty().bindBidirectional(model.userNameProperty());
    createUserButton.disableProperty().bind(model.createUserDisabledProperty());
  }

  public void run() {
    onMainWindowBoundsQuery.accept(new MainWindowBoundsQuery());
    onPreferencesQuery.accept(new PreferencesQuery());
    onNewestUserQuery.accept(new NewestUserQuery());
  }

  public void display(MainWindowBoundsQueryResult result) {
    System.out.println(result);
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
    System.out.println(result);
    model.setGreeting(result.greeting());
    preferencesController.display(result);
  }

  public void display(NewestUserQueryResult result) {
    System.out.println(result);
    model.setNewUser(result.user());
  }

  public void display(Failure failure) {
    var index = failure.errorMessage().indexOf(": ");
    var header = index == -1 ? null : failure.errorMessage().substring(0, index);
    var content =
        index == -1 ? failure.errorMessage() : failure.errorMessage().substring(index + 1);

    var alert = new Alert(AlertType.ERROR);
    alert.initOwner(stage);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.show();
  }

  @FXML
  private void handleShowPreferences() {
    preferencesController.setOnChangePreferencesCommand(onChangePreferencesCommand);
    preferencesController.run();
  }

  @FXML
  private void handleClose() {
    onChangeMainWindowBoundsCommand.accept(
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
    if (model.isCreateUserDisabled()) {
      return;
    }

    onCreateUserCommand.accept(new CreateUserCommand(model.getUserName()));
  }
}
