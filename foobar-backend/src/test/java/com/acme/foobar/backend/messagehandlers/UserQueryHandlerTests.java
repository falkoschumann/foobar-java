/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.foobar.backend.adapters.MemoryUserRepository;
import com.acme.foobar.contract.data.User;
import com.acme.foobar.contract.messages.queries.UserQuery;
import com.acme.foobar.contract.messages.queries.UserQueryResult;
import org.junit.jupiter.api.Test;

class UserQueryHandlerTests {
  @Test
  void handleSuccessfully() {
    var repository = new MemoryUserRepository();
    repository.setCurrentUser(new User("#1", "Alice"));
    var handler = new UserQueryHandler(repository);

    var result = handler.handle(new UserQuery());

    assertEquals(new UserQueryResult(new User("#1", "Alice")), result);
  }
}
