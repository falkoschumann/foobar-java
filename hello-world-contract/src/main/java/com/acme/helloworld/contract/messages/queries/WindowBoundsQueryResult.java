/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.queries;

public record WindowBoundsQueryResult(double x, double y, double width, double height) {
  public boolean isNull() {
    return x == 0.0 && y == 0.0 && width == 0.0 && height == 0.0;
  }
}
