/*
 * Foobar - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.queries.UserQuery;
import com.acme.helloworld.contract.messages.queries.UserQueryResult;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class MainViewController {
  @Getter @Setter private Runnable onOpenInfo;
  @Getter @Setter private Consumer<CreateUserCommand> onCreateUserCommand;
  @Getter @Setter private Consumer<UserQuery> onUserQuery;

  @FXML private HBox commandBar;
  @FXML private Label greeting;

  public static MainViewController create(Stage stage) {
    var factory = new ViewControllerFactory(MainViewController.class);

    var scene = new Scene(factory.getView());
    stage.setScene(scene);
    stage.setTitle("Hello World");
    stage.setMinWidth(320);
    stage.setMinHeight(569);

    return factory.getController();
  }

  private Stage getWindow() {
    return (Stage) commandBar.getScene().getWindow();
  }

  public void run() {
    getWindow().show();
    onUserQuery.accept(new UserQuery(null));
  }

  public void display(UserQueryResult result) {
    greeting.setText("Hello " + result.user().name());
  }

  @FXML
  private void initialize() {}

  @FXML
  private void handleOpenInfo() {
    onOpenInfo.run();
  }
}
