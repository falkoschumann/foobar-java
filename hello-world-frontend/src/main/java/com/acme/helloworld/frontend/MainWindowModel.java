/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.User;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

class MainWindowModel {
  @Getter @Setter private String greeting;

  boolean isRunningOnMac() {
    return System.getProperty("os.name").toLowerCase().contains("mac");
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

  void userAdded(User user) {
    if (user == null) {
      return;
    }

    var s = getGreeting().replace("$user", user.name());
    setUserGreeting(s);
    setUserName("");
  }
}
