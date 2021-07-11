/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;

public class UsersQueryHandler {
  private final UserRepository repository;

  public UsersQueryHandler(UserRepository repository) {
    this.repository = repository;
  }

  public UsersQueryResult handle(UsersQuery query) {
    try {
      var users = repository.findAll();
      return new UsersQueryResult(users);
    } catch (Exception e) {
      return new UsersQueryResult(null);
    }
  }
}
