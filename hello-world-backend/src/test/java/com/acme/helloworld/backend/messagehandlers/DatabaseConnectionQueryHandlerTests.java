/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.DatabaseConnection;
import com.acme.helloworld.contract.messages.queries.DatabaseConnectionQuery;
import com.acme.helloworld.contract.messages.queries.DatabaseConnectionQueryResult;
import org.junit.jupiter.api.Test;

class DatabaseConnectionQueryHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new DatabaseConnectionQueryHandler(repository);

    var result = handler.handle(new DatabaseConnectionQuery());

    assertEquals(
        new DatabaseConnectionQueryResult(
            new DatabaseConnection(true, "localhost", 5432, "acme_test", "acme_test", "acme_test")),
        result);
  }
}
