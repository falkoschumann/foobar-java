/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.DataSourceFactory;
import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.backend.adapters.SqlUserRepository;
import com.acme.helloworld.contract.messages.commands.CreateUserCommand;
import com.acme.helloworld.contract.messages.commands.Success;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CreateUserCommandHandlerTests {
  private static DataSource dataSource;

  @BeforeAll
  static void initAll() {
    var host = System.getenv("CI") != null ? "postgres" : "localhost";
    dataSource =
        DataSourceFactory.createDataSource(host, 5432, "acme_test", "acme_test", "acme_test");
  }

  @Test
  void handleSuccessfully_Memory() {
    var repository = new MemoryUserRepository();
    repository.initWithTestdata();
    var handler = new CreateUserCommandHandler(repository);

    var status = handler.handle(new CreateUserCommand("Bob"));

    assertEquals(new Success(), status);
    var user = repository.findAll().get(1);
    assertThat(user.id(), is(any(String.class)));
    assertEquals("Bob", user.name());
  }

  @Test
  void handleSuccessfully_Database() {
    var repository = new SqlUserRepository(dataSource);
    var handler = new CreateUserCommandHandler(repository);

    var status = handler.handle(new CreateUserCommand("Bob"));

    assertEquals(new Success(), status);
    var user = repository.findAll().get(1);
    assertThat(user.id(), is(any(String.class)));
    assertEquals("Bob", user.name());
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
