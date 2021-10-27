/*
 * Hello World - Frontend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

class PreferencesModel {
  private ObservableList<String> greetingWarningStyleClass;

  // region Constructors

  void initGreetingWarningStyleClass(ObservableList<String> list) {
    this.greetingWarningStyleClass = list;
  }

  // endregion

  // region Properties

  public StringProperty greetingProperty() {
    return greeting;
  }

  private final StringProperty greeting =
      new SimpleStringProperty("") {
        @Override
        protected void invalidated() {
          if (get().isBlank()) {
            greetingWarning.set("Greeting should not be empty.");
            addClass(greetingWarningStyleClass, "warning");
          } else if (!get().contains("$user")) {
            greetingWarning.set("Greeting should contain placeholder $user.");
            addClass(greetingWarningStyleClass, "warning");
          } else {
            greetingWarning.set(
                "Greeting template, the placeholder $user will be replaced with user name.");
            removeClass(greetingWarningStyleClass, "warning");
          }
        }
      };

  public StringProperty greetingWarningProperty() {
    return greetingWarning;
  }

  private final StringProperty greetingWarning = new SimpleStringProperty("");

  // endregion

  // region Methods

  private void addClass(ObservableList<String> styleClass, String className) {
    if (styleClass.contains(className)) {
      return;
    }

    styleClass.add(className);
  }

  private void removeClass(ObservableList<String> styleClass, String className) {
    styleClass.remove(className);
  }

  // endregion
}
