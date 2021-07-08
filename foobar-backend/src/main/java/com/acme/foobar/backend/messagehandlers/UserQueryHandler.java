/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.messagehandlers;

import com.acme.foobar.backend.UserRepository;
import com.acme.foobar.contract.messages.queries.UserQuery;
import com.acme.foobar.contract.messages.queries.UserQueryResult;

public class UserQueryHandler {
  private final UserRepository repository;

  public UserQueryHandler(UserRepository repository) {
    this.repository = repository;
  }

  public UserQueryResult handle(UserQuery query) {
    try {
      var user = repository.findById(query.id());
      return new UserQueryResult(user.orElse(null));
    } catch (Exception e) {
      return new UserQueryResult(null);
    }
  }
}
