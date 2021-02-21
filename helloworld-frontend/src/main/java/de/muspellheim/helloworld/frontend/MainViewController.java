/*
 * User Stories - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.frontend;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class MainViewController {

  @Getter @Setter private Runnable onOpenInfo;

  @FXML private HBox commandBar;

  public static MainViewController create(Stage stage) {
    var factory = new ViewControllerFactory(MainViewController.class);

    var scene = new Scene(factory.getView());
    stage.setScene(scene);
    stage.setTitle("User Stories");
    stage.setMinWidth(320);
    stage.setMinHeight(569);

    return factory.getController();
  }

  private Stage getWindow() {
    return (Stage) commandBar.getScene().getWindow();
  }

  public void run() {
    getWindow().show();
  }

  public void display(Object result) {}

  @FXML
  private void initialize() {}

  @FXML
  private void handleOpenInfo() {
    onOpenInfo.run();
  }
}
