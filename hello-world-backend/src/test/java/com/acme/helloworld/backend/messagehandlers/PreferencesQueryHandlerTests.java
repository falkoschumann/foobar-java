/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQueryResult;
import org.junit.jupiter.api.Test;

class PreferencesQueryHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new PreferencesQueryHandler(repository);

    var result = handler.handle(new PreferencesQuery());

    assertEquals(
        new PreferencesQueryResult(
            new DatabaseConnection(true, "localhost", 5432, "acme_test", "acme_test", "acme_test")),
        result);
  }
}
