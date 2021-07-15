/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.messages.notifications.DatabaseConnectionFaultyNotification;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

public class UsersQueryHandler {
  @Getter @Setter
  private Consumer<DatabaseConnectionFaultyNotification> onDatabaseConnectionFaultyNotification;

  private final UserRepository repository;

  public UsersQueryHandler(UserRepository repository) {
    this.repository = repository;
  }

  public UsersQueryResult handle(UsersQuery query) {
    try {
      var users = repository.findAll();
      return new UsersQueryResult(users);
    } catch (RuntimeException e) {
      onDatabaseConnectionFaultyNotification.accept(
          new DatabaseConnectionFaultyNotification(e.getLocalizedMessage()));
      return new UsersQueryResult(null);
    }
  }
}
