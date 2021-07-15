package com.acme.helloworld.contract.messages.notifications;

import java.util.Objects;

public record DatabaseConnectionFaultyNotification(String errorMessage) {
public DatabaseConnectionFaultyNotification{
  Objects.requireNonNull(errorMessage,"errorMessage");
}
}
