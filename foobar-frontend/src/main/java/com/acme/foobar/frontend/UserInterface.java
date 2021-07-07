/*
 * Foobar - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.frontend;

import com.acme.foobar.contract.MessageHandling;
import java.net.URL;
import java.util.Properties;
import javafx.stage.Stage;

public class UserInterface {
  private final MainViewController mainViewController;

  public UserInterface(
      MessageHandling messageHandling, Stage primaryStage, URL appIcon, Properties appProperties) {
    mainViewController = MainViewController.create(primaryStage);

    var infoStage = new Stage();
    infoStage.initOwner(primaryStage);
    var infoViewController = InfoViewController.create(infoStage);
    infoViewController.setIcon(appIcon.toString());
    infoViewController.setTitle(appProperties.getProperty("title"));
    infoViewController.setVersion(appProperties.getProperty("version"));
    infoViewController.setCopyright(appProperties.getProperty("copyright"));

    mainViewController.setOnOpenInfo(infoStage::show);
    mainViewController.setOnCreateUserCommand(messageHandling::handle);
    mainViewController.setOnUserQuery(
        query -> {
          var result = messageHandling.handle(query);
          mainViewController.display(result);
        });
  }

  public void run() {
    mainViewController.run();
  }
}
