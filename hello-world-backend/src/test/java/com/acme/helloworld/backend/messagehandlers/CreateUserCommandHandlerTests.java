/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.TestDataSourceFactory;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CreateUserCommandHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryUserRepository();
    repository.addExamples();
    var handler = new CreateUserCommandHandler(repository);

    var status = handler.handle(new CreateUserCommand("Bob"));

    assertAll(
        () -> assertEquals(new Success(), status),
        () -> assertThat(repository.findAll().get(1).id(), is(any(String.class))),
        () -> assertEquals("Bob", repository.findAll().get(1).name()));
  }

  @Nested
  @Tag("sql")
  class Database {
    private static DataSource dataSource;

    @BeforeAll
    static void initAll() {
      dataSource = TestDataSourceFactory.createDataSource();
    }

    @Test
    void testHandle() {
      var repository = new SqlUserRepository(dataSource);
      var handler = new CreateUserCommandHandler(repository);

      var status = handler.handle(new CreateUserCommand("Bob"));

      assertAll(
          () -> assertEquals(new Success(), status),
          () -> assertThat(repository.findAll().get(1).id(), is(any(String.class))),
          () -> assertEquals("Bob", repository.findAll().get(1).name()));
    }

    @AfterEach
    void tearDown() throws Exception {
      var sql = """
      DELETE FROM users
      WHERE name = 'Bob'
      """;
      try (var connection = dataSource.getConnection()) {
        try (var statement = connection.prepareStatement(sql)) {
          statement.executeUpdate();
        }
      }
    }
  }
}
