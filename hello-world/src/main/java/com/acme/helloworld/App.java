/*
 * Hello World - Application
 * Copyright (c) 2020 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.UsersQueryHandler;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.frontend.HelloWorldController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private UserRepository userRepository;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void init() throws Exception {
    // TODO Demo mode and SQL database
    userRepository = new MemoryUserRepository();
    userRepository.createUser(new User("Alice"));
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    var createUserCommandHandler = new CreateUserCommandHandler(userRepository);
    var usersQueryHandler = new UsersQueryHandler(userRepository);
    var frontend = HelloWorldController.create(primaryStage);

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

    frontend.run();
  }
}
