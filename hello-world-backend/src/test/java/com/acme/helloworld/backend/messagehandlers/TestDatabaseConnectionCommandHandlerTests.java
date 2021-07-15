package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.commands.Success;
import com.acme.helloworld.contract.messages.commands.TestDatabaseConnectionCommand;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("sql")
class TestDatabaseConnectionCommandHandlerTests {
  @Test
  void testHandle_Success() {
    var handler = new TestDatabaseConnectionCommandHandler();

    var status =
        handler.handle(
            new TestDatabaseConnectionCommand(
                "localhost", 5432, "acme_test", "acme_test", "acme_test"));

    assertEquals(new Success(), status);
  }

  @Test
  void testHandle_Failure() {
    var handler = new TestDatabaseConnectionCommandHandler();

    var status =
        handler.handle(
            new TestDatabaseConnectionCommand("localhost", 5432, "acme", "acme", "acme"));

    assertTrue(status instanceof Failure);
  }
}
