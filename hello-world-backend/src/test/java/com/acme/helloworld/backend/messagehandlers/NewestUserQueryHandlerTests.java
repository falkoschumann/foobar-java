/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryEventStore;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;
import org.junit.jupiter.api.Test;

class NewestUserQueryHandlerTests {
  @Test
  void testHandle() {
    var eventStore = new MemoryEventStore();
    eventStore.addExamples();
    var handler = new NewestUserQueryHandler(eventStore);

    var result = handler.handle(new NewestUserQuery());

    assertEquals(
        new NewestUserQueryResult(new User("0dc31588-bda7-4987-adc5-ad4413fc3e54", "Alice")),
        result);
  }
}
