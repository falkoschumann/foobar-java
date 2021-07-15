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
import com.acme.helloworld.backend.messagehandlers.*;
import com.acme.helloworld.backend.messagehandlers.ChangeMainWindowBoundsCommandHandler;
import com.acme.helloworld.backend.messagehandlers.ChangePreferencesCommandHandler;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.PreferencesQueryHandler;
import com.acme.helloworld.backend.messagehandlers.UsersQueryHandler;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
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
    var changeMainWindowBoundsCommandHandler =
        new ChangeMainWindowBoundsCommandHandler(preferencesRepository);
    var changePreferencesCommandHandler =
        new ChangePreferencesCommandHandler(preferencesRepository);
    var createUserCommandHandler = new CreateUserCommandHandler(userRepository);
    var testDatabaseConnectionCommandHandler = new TestDatabaseConnectionCommandHandler();
    var mainWindowBoundsQueryHandler = new MainWindowBoundsQueryHandler(preferencesRepository);
    var preferencesQueryHandler = new PreferencesQueryHandler(preferencesRepository);
    var usersQueryHandler = new UsersQueryHandler(userRepository);
    var frontend = HelloWorldController.create(primaryStage);

    testDatabaseConnectionCommandHandler.setOnDatabaseConnectionFaultyNotification(
        frontend::display);
    testDatabaseConnectionCommandHandler.setOnDatabaseConnectionSuccessfulNotification(
        frontend::display);
    usersQueryHandler.setOnDatabaseConnectionFaultyNotification(frontend::display);
    frontend.setOnChangeMainWindowBoundsCommand(changeMainWindowBoundsCommandHandler::handle);
    frontend.setOnChangePreferencesCommand(
        command -> {
          changePreferencesCommandHandler.handle(command);
          var result = preferencesQueryHandler.handle(new PreferencesQuery());
          frontend.display(result);
        });
    frontend.setOnCreateUserCommand(
        command -> {
          createUserCommandHandler.handle(command);
          var result = usersQueryHandler.handle(new UsersQuery());
          frontend.display(result);
        });
    frontend.setOnTestDatabaseConnectionCommand(testDatabaseConnectionCommandHandler::handle);
    frontend.setOnMainWindowBoundsQuery(
        query -> {
          var result = mainWindowBoundsQueryHandler.handle(query);
          frontend.display(result);
        });
    frontend.setOnPreferencesQuery(
        query -> {
          var result = preferencesQueryHandler.handle(query);
          frontend.display(result);
        });
    frontend.setOnUsersQuery(
        query -> {
          var result = usersQueryHandler.handle(query);
          frontend.display(result);
        });

    frontend.run();
  }
}
