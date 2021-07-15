/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import com.acme.helloworld.contract.data.User;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class HelloWorldModel {
  boolean isRunningOnMac() {
    return System.getProperty("os.name").toLowerCase().contains("mac");
  }

  private final ObjectProperty<List<User>> users =
      new SimpleObjectProperty<>(List.of()) {
        @Override
        protected void invalidated() {
          var value = getValue();
          if (value.isEmpty()) {
            return;
          }

          var last = value.get(value.size() - 1);
          newestUser.set(last);
        }
      };

  final void setUsers(List<User> users) {
    this.users.set(users);
  }

  private final ObjectProperty<User> newestUser = new SimpleObjectProperty<>();

  final User getNewestUser() {
    return newestUser.get();
  }

  final ObjectProperty<User> newestUserProperty() {
    return newestUser;
  }
}
