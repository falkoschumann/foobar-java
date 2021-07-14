/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.backend.adapters.MemoryUserRepository;
import com.acme.helloworld.backend.adapters.SqlUserRepository;
import com.acme.helloworld.backend.adapters.TestDataSourceFactory;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.UsersQuery;
import com.acme.helloworld.contract.messages.queries.UsersQueryResult;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UserQueryHandlerTests {
  @Test
  void testHandle() {
    var repository = new MemoryUserRepository();
    repository.addExamples();
    var handler = new UsersQueryHandler(repository);

    var result = handler.handle(new UsersQuery());

    assertEquals(
        new UsersQueryResult(List.of(new User("0dc31588-bda7-4987-adc5-ad4413fc3e54", "Alice"))),
        result);
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
      var handler = new UsersQueryHandler(repository);

      var result = handler.handle(new UsersQuery());

      assertEquals(
          new UsersQueryResult(List.of(new User("0dc31588-bda7-4987-adc5-ad4413fc3e54", "Alice"))),
          result);
    }
  }
}
