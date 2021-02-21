/*
 * Hello World - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.muspellheim.helloworld.backend.adapters.MemoryUserRepository;
import de.muspellheim.helloworld.contract.data.User;
import de.muspellheim.helloworld.contract.messages.queries.UserQuery;
import de.muspellheim.helloworld.contract.messages.queries.UserQueryResult;
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
