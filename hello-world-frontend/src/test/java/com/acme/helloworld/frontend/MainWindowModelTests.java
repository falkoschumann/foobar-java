/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.acme.helloworld.contract.data.User;
import org.junit.jupiter.api.Test;

class MainWindowModelTests {
  @Test
  void testUserAdded() {
    var model = new MainWindowModel();
    model.setGreeting("Hello $user");
    model.setUserName("Alice");

    model.userAdded(new User("1", "Alice"));

    assertAll(
        () -> assertEquals("Hello Alice", model.getUserGreeting(), "userGreeting"),
        () -> assertEquals("", model.getUserName(), "userName"));
  }
}
