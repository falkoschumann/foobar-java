/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

public record Bounds(double x, double y, double width, double height) {
  public static final Bounds NULL = new Bounds(0, 0, 0, 0);
}
