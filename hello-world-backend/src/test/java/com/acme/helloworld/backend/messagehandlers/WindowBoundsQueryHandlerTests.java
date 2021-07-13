/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.WindowBounds;
import com.acme.helloworld.contract.messages.queries.WindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.WindowBoundsQueryResult;
import org.junit.jupiter.api.Test;

class WindowBoundsQueryHandlerTests {
  @Test
  void handleSuccessfully_Memory() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new WindowBoundsQueryHandler(repository);

    var result = handler.handle(new WindowBoundsQuery());

    assertEquals(new WindowBoundsQueryResult(new WindowBounds(36, 24, 640, 480)), result);
  }
}
