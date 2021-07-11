/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserQueryHandlerTests {
  @Test
  void handleSuccessfully() {
    var repository = new MemoryUserRepository();
    repository.createUser(new User("#1", "Alice"));
    var handler = new UsersQueryHandler(repository);

    var result = handler.handle(new UsersQuery());

    assertEquals(new UsersQueryResult(List.of(new User("#1", "Alice"))), result);
  }
}
