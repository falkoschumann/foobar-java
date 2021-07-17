/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.User;
import java.util.Optional;
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

  private final ReadOnlyStringWrapper userGreeting = new ReadOnlyStringWrapper("");

  final String getUserGreeting() {
    return userGreeting.get();
  }

  private void setUserGreeting(String value) {
    userGreeting.set(value);
  }

  final ReadOnlyStringProperty userGreetingProperty() {
    return userGreeting.getReadOnlyProperty();
  }

  private final StringProperty userName = new SimpleStringProperty("");

  final String getUserName() {
    return userName.get();
  }

  final void setUserName(String value) {
    userName.set(value);
  }

  final StringProperty userNameProperty() {
    return userName;
  }

  void userAdded(User user) {
    var name = Optional.ofNullable(user).map(User::name).orElse("");
    var s = getGreeting().replace("$user", name);
    setUserGreeting(s);
    setUserName("");
  }
}
