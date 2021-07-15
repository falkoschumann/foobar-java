/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

public record WindowBounds(double x, double y, double width, double height) {
  public static final WindowBounds NULL = new WindowBounds(0, 0, 0, 0);
}
