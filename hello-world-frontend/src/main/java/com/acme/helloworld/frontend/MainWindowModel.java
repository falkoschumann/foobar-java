/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class MainWindowModel {
  final StringProperty greetingProperty() {
    return greeting;
  }

  private final StringProperty greeting =
      new SimpleStringProperty("") {
        @Override
        protected void invalidated() {
          userName.set("");
        }
      };

  final StringProperty userNameProperty() {
    return userName;
  }

  private final StringProperty userName =
      new SimpleStringProperty("") {
        @Override
        protected void invalidated() {
          createUserButtonDisable.set(getValue().isBlank());
        }
      };

  final ReadOnlyBooleanProperty createUserButtonDisableProperty() {
    return createUserButtonDisable.getReadOnlyProperty();
  }

  private final ReadOnlyBooleanWrapper createUserButtonDisable = new ReadOnlyBooleanWrapper(true);
}
