/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreferencesModelTests {
  private static PreferencesModel model;
  private static ObservableList<String> greetingWarningStyleClass;

  @BeforeAll
  static void initAll() {
    model = new PreferencesModel();
    greetingWarningStyleClass = FXCollections.observableArrayList();
    model.initGreetingWarningStyleClass(greetingWarningStyleClass);
  }

  @Test
  @Order(1)
  void step1InitialState() {
    assertAll(
        () -> assertEquals("", model.greetingProperty().get(), "greeting"),
        () -> assertEquals("", model.greetingWarningProperty().get(), "greetingWarning"),
        () -> assertEquals(List.of(), greetingWarningStyleClass, "greetingWarningStyleClass"));
  }

  @Test
  @Order(2)
  void step2ChangeGreeting() {
    model.greetingProperty().set("Konnichiwa $user");

    assertAll(
        () -> assertEquals("Konnichiwa $user", model.greetingProperty().get(), "greeting"),
        () ->
            assertEquals(
                "Greeting template, the placeholder $user will be replaced with user name.",
                model.greetingWarningProperty().get(),
                "greetingWarning"),
        () -> assertEquals(List.of(), greetingWarningStyleClass, "greetingWarningStyleClass"));
  }

  @Test
  @Order(2)
  void step2ChangeGreeting_Empty() {
    model.greetingProperty().set("Konnichiwa $user");

    model.greetingProperty().set("");

    assertAll(
        () -> assertEquals("", model.greetingProperty().get(), "greeting"),
        () ->
            assertEquals(
                "Greeting should not be empty.",
                model.greetingWarningProperty().get(),
                "greetingWarning"),
        () ->
            assertEquals(
                List.of("warning"), greetingWarningStyleClass, "greetingWarningStyleClass"));
  }

  @Test
  @Order(3)
  void step2ChangeGreeting_NoPlaceholder() {
    model.greetingProperty().set("Konnichiwa $user");

    model.greetingProperty().set("Hello user");

    assertAll(
        () -> assertEquals("Hello user", model.greetingProperty().get(), "greeting"),
        () ->
            assertEquals(
                "Greeting should contain placeholder $user.",
                model.greetingWarningProperty().get(),
                "greetingWarning"),
        () ->
            assertEquals(
                List.of("warning"), greetingWarningStyleClass, "greetingWarningStyleClass"));
  }
}
