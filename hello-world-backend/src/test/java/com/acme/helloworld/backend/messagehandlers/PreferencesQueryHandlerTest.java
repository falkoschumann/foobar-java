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

class PreferencesQueryHandlerTest {
  @Test
  void handle_success() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new PreferencesQueryHandler(repository);

    var result = handler.handle(new PreferencesQuery());

    assertEquals(new PreferencesQueryResult("Konnichiwa $user"), result);
  }
}
