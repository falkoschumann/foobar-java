/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class MainWindowModel {
  private final StringProperty greeting =
      new SimpleStringProperty() {
        @Override
        protected void invalidated() {
          if (getNewUser() == null) {
            return;
          }

          var s = getGreeting().replace("$user", getNewUser().name());
          setUserGreeting(s);
        }
      };

  private String getGreeting() {
    return greeting.get();
  }

  final void setGreeting(String value) {
    greeting.set(value);
  }

  private final ObjectProperty<User> newUser =
      new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
          var s = getGreeting().replace("$user", getValue().name());
          setUserGreeting(s);
          setUserName("");
        }
      };

  private User getNewUser() {
    return newUser.get();
  }

  final void setNewUser(User value) {
    newUser.set(value);
  }

  private final ReadOnlyStringWrapper userGreeting = new ReadOnlyStringWrapper("Hello");

  final String getUserGreeting() {
    return userGreeting.get();
  }

  private void setUserGreeting(String value) {
    userGreeting.set(value);
  }

  final ReadOnlyStringProperty userGreetingProperty() {
    return userGreeting.getReadOnlyProperty();
  }

  private final StringProperty userName =
      new SimpleStringProperty("") {
        @Override
        protected void invalidated() {
          setCreateUserDisabled(getValue().isBlank());
        }
      };

  final String getUserName() {
    return userName.get();
  }

  final void setUserName(String value) {
    userName.set(value);
  }

  final StringProperty userNameProperty() {
    return userName;
  }

  private final ReadOnlyBooleanWrapper createUserDisabled = new ReadOnlyBooleanWrapper(true);

  final boolean isCreateUserDisabled() {
    return createUserDisabled.get();
  }

  private void setCreateUserDisabled(boolean createUserDisabled) {
    this.createUserDisabled.set(createUserDisabled);
  }

  final ReadOnlyBooleanProperty createUserDisabledProperty() {
    return createUserDisabled.getReadOnlyProperty();
  }
}
