/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.notifications;

import java.util.Objects;

public record UserNotCreatedNotification(String errorMessage) {
  public UserNotCreatedNotification {
    Objects.requireNonNull(errorMessage, "errorMessage");
  }
}
