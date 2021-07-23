/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.acme.helloworld.contract.data.User;
import org.junit.jupiter.api.Test;

class MainWindowModelTests {
  @Test
  void testInitialState() {
    var model = new MainWindowModel();
    model.setGreeting("Hello $user");

    assertAll(
        () -> assertEquals("Hello", model.getUserGreeting(), "userGreeting"),
        () -> assertEquals("", model.getUserName(), "userName"),
        () -> assertTrue(model.isCreateUserDisabled(), "createUserDisabled"));
  }

  @Test
  void testSetUserName() {
    var model = new MainWindowModel();
    model.setGreeting("Hello $user");

    model.setUserName("Alice");

    assertAll(
        () -> assertEquals("Hello", model.getUserGreeting(), "userGreeting"),
        () -> assertEquals("Alice", model.getUserName(), "userName"),
        () -> assertFalse(model.isCreateUserDisabled(), "createUserDisabled"));
  }

  @Test
  void testUserAdded() {
    var model = new MainWindowModel();
    model.setGreeting("Hello $user");
    model.setUserName("Alice");

    model.setNewUser(new User("Alice"));

    assertAll(
        () -> assertEquals("Hello Alice", model.getUserGreeting(), "userGreeting"),
        () -> assertEquals("", model.getUserName(), "userName"),
        () -> assertTrue(model.isCreateUserDisabled(), "createUserDisabled"));
  }
}
