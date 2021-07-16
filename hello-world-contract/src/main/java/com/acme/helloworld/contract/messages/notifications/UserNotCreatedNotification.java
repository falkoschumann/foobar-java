package com.acme.helloworld.contract.messages.notifications;

import java.util.Objects;

public record UserNotCreatedNotification(String errorMessage) {
public UserNotCreatedNotification{
  Objects.requireNonNull(errorMessage,"errorMessage");
}
}
