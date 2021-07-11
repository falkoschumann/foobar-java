/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class HelloWorldController {
  @Getter @Setter private Consumer<CreateUserCommand> onCreateUserCommand;
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
    stage.show();
    onUsersQuery.accept(new UsersQuery());
  }

  public void display(UsersQueryResult result) {
    model.setUsers(result.users());
  }

  @FXML
  private void initialize() {
    model = new HelloWorldModel();
    if (model.isRunningOnMac()) {
      menuBar.setUseSystemMenuBar(true);
    }

    model
        .newestUserProperty()
        .addListener(o -> greeting.setText("Hello " + model.getNewestUser().name()));
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
