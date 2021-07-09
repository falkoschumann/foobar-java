/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.UserQuery;
import com.acme.helloworld.contract.messages.queries.UserQueryResult;
import org.junit.jupiter.api.Test;

class UserQueryHandlerTests {
  @Test
  void handleSuccessfully() {
    var repository = new MemoryUserRepository();
    repository.createUser(new User("#1", "Alice"));
    var handler = new UserQueryHandler(repository);

    var result = handler.handle(new UserQuery("#1"));

    assertEquals(new UserQueryResult(new User("#1", "Alice")), result);
  }
}
