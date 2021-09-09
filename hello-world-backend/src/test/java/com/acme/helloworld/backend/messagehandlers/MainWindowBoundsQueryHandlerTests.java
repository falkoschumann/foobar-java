/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.contract.data.Bounds;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQuery;
import com.acme.helloworld.contract.messages.queries.MainWindowBoundsQueryResult;
import org.junit.jupiter.api.Test;

class MainWindowBoundsQueryHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryPreferencesRepository();
    repository.addExamples();
    var handler = new MainWindowBoundsQueryHandler(repository);

    var result = handler.handle(new MainWindowBoundsQuery());

    assertEquals(new MainWindowBoundsQueryResult(new Bounds(360, 240, 640, 480)), result);
  }
}
