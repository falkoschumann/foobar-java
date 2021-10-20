/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainWindowModelTests {
  private static MainWindowModel model;

  @BeforeAll
  static void initAll() {
    model = new MainWindowModel();
  }

  @Test
  @Order(1)
  void step1InitialState() {
    assertAll(
        () -> assertEquals("", model.greetingProperty().get(), "greeting"),
        () -> assertEquals("", model.userNameProperty().get(), "userName"),
        () -> assertTrue(model.createUserButtonDisableProperty().get(), "createUserButtonDisable"));
  }

  @Test
  @Order(2)
  void step2EditUserName() {
    model.userNameProperty().set("Alice");

    assertAll(
        () -> assertEquals("", model.greetingProperty().get(), "greeting"),
        () -> assertEquals("Alice", model.userNameProperty().get(), "userName"),
        () ->
            assertFalse(model.createUserButtonDisableProperty().get(), "createUserButtonDisable"));
  }

  @Test
  @Order(3)
  void step3UpdateGreeting() {
    model.greetingProperty().set("Hello Alice");

    assertAll(
        () -> assertEquals("Hello Alice", model.greetingProperty().get(), "greeting"),
        () -> assertEquals("", model.userNameProperty().get(), "userName"),
        () -> assertTrue(model.createUserButtonDisableProperty().get(), "createUserButtonDisable"));
  }
}
