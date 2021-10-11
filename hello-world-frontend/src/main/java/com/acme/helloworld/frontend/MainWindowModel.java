/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.User;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class MainWindowModel {
  private final StringProperty userName =
      new SimpleStringProperty("") {
        @Override
        protected void invalidated() {
          createUserButtonDisable.set(getValue().isBlank());
        }
      };

  StringProperty userNameProperty() {
    return userName;
  }

  private final BooleanProperty createUserButtonDisable = new SimpleBooleanProperty(true);

  BooleanExpression createUserButtonDisableProperty() {
    return createUserButtonDisable;
  }

  private final StringProperty greeting = new SimpleStringProperty("");

  StringExpression greetingProperty() {
    return greeting;
  }

  private String greetingTemplate;

  void updateGreetingTemplate(String template) {
    greetingTemplate = template;
    String baseGreeting = template.replace("$user", "").strip();
    greeting.set(baseGreeting);
  }

  void updateNewestUser(User user) {
    String s = greetingTemplate.replace("$user", user.name());
    greeting.set(s);
    userName.set("");
  }
}
