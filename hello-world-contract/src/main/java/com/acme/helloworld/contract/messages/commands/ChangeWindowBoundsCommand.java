/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

public record ChangeWindowBoundsCommand(double x, double y, double width, double height) {}
