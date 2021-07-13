/*
 * Hello World - Application
 * Copyright (c) 2020 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.backend.adapters.DataSourceFactory;
import com.acme.helloworld.backend.adapters.JavaPreferencesRepository;
import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.backend.adapters.SqlUserRepository;
import com.acme.helloworld.backend.messagehandlers.ChangeWindowBoundsCommandHandler;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.UsersQueryHandler;
import com.acme.helloworld.backend.messagehandlers.WindowBoundsChangedNotificationHandler;
import com.acme.helloworld.backend.messagehandlers.WindowBoundsQueryHandler;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.frontend.HelloWorldController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private UserRepository userRepository;
  private PreferencesRepository preferencesRepository;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void init() {
    if (getParameters().getUnnamed().contains("--demo")) {
      userRepository = new MemoryUserRepository().addExamples();
      preferencesRepository = new MemoryPreferencesRepository();
    } else {
      var dataSource =
          DataSourceFactory.createDataSource(
              "localhost", 5432, "acme_test", "acme_test", "acme_test");
      userRepository = new SqlUserRepository(dataSource);
      preferencesRepository = new JavaPreferencesRepository();
    }
  }

  @Override
  public void start(Stage primaryStage) {
    var createUserCommandHandler = new CreateUserCommandHandler(userRepository);
    var changeWindowBoundsCommandHandler =
        new ChangeWindowBoundsCommandHandler(preferencesRepository);
    var usersQueryHandler = new UsersQueryHandler(userRepository);
    var windowBoundsQueryHandler = new WindowBoundsQueryHandler(preferencesRepository);
    var windowBoundsChangedNotificationHandler = new WindowBoundsChangedNotificationHandler();
    var frontend = HelloWorldController.create(primaryStage);

    windowBoundsChangedNotificationHandler.setOnChangeWindowBoundsCommand(
        changeWindowBoundsCommandHandler::handle);
    frontend.setOnCreateUserCommand(
        c -> {
          createUserCommandHandler.handle(c);
          var result = usersQueryHandler.handle(new UsersQuery());
          frontend.display(result);
        });
    frontend.setOnUsersQuery(
        q -> {
          var result = usersQueryHandler.handle(q);
          frontend.display(result);
        });
    frontend.setOnWindowBoundsQuery(
        q -> {
          var result = windowBoundsQueryHandler.handle(q);
          frontend.display(result);
        });
    frontend.setOnWindowBoundsChangedNotification(windowBoundsChangedNotificationHandler::handle);

    frontend.run();
  }
}
