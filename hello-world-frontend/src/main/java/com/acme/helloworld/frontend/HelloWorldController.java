/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.WindowBounds;
import com.acme.helloworld.contract.messages.commands.ChangeMainWindowBoundsCommand;
import com.acme.helloworld.contract.messages.commands.ChangePreferencesCommand;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.TestDatabaseConnectionCommand;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class HelloWorldController {
  @Getter @Setter private Consumer<ChangeMainWindowBoundsCommand> onChangeMainWindowBoundsCommand;
  @Getter @Setter private Consumer<ChangePreferencesCommand> onChangePreferencesCommand;
  @Getter @Setter private Consumer<CreateUserCommand> onCreateUserCommand;
  @Getter @Setter private Consumer  <TestDatabaseConnectionCommand> onTestDatabaseConnectionCommand;
  @Getter @Setter private Consumer<MainWindowBoundsQuery> onMainWindowBoundsQuery;
  @Getter @Setter private Consumer<PreferencesQuery> onPreferencesQuery;
  @Getter @Setter private Consumer<UsersQuery> onUsersQuery;

  @FXML private Stage stage;
  @FXML private MenuBar menuBar;
  @FXML private Label greeting;
  @FXML private TextField name;

  private HelloWorldModel model;

  public static HelloWorldController create(Stage stage) {
    try {
      var location = HelloWorldController.class.getResource("HelloWorldView.fxml");
      var resources = ResourceBundle.getBundle("HelloWorld");
      var loader = new FXMLLoader(location, resources);
      loader.setRoot(stage);
      loader.load();
      return loader.getController();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void run() {
    onUsersQuery.accept(new UsersQuery());
    onMainWindowBoundsQuery.accept(new MainWindowBoundsQuery());
    stage.show();
  }

  public void display(UsersQueryResult result) {
    model.setUsers(result.users());
  }

  public void display(MainWindowBoundsQueryResult result) {
    if (WindowBounds.NULL.equals(result.windowBounds())) {
      return;
    }

    var x = result.x();
    var y = result.y();
    var width = result.width();
    var height = result.height();

    if (Screen.getScreensForRectangle(x, y, width, height).isEmpty()) {
      return;
    }

    stage.setX(x);
    stage.setY(y);
    stage.setWidth(width);
    stage.setHeight(height);
  }

  @FXML
  private void initialize() {
    model = new HelloWorldModel();
    if (model.isRunningOnMac()) {
      menuBar.setUseSystemMenuBar(true);
    }

    stage.xProperty().addListener(o -> windowBoundsChanged());
    stage.yProperty().addListener(o -> windowBoundsChanged());
    stage.widthProperty().addListener(o -> windowBoundsChanged());
    stage.heightProperty().addListener(o -> windowBoundsChanged());
    model
        .newestUserProperty()
        .addListener(o -> greeting.setText("Hello " + model.getNewestUser().name()));
  }

  private void windowBoundsChanged() {
    onChangeMainWindowBoundsCommand.accept(
        new ChangeMainWindowBoundsCommand(
            stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()));
  }

  @FXML
  private void handleShowPreferences() {
    var controller = PreferencesController.create(stage);
    controller.run();
  }

  @FXML
  private void handleClose() {
    stage.close();
  }

  @FXML
  private void handleShowAbout() {
    var controller = AboutController.create(stage);
    controller.run();
  }

  @FXML
  private void handleCreateUser() {
    onCreateUserCommand.accept(new CreateUserCommand(name.getText()));
  }
}
