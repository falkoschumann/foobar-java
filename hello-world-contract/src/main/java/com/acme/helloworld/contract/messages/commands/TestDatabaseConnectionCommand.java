/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.commands;

public record TestDatabaseConnectionCommand(
    String host, int port, String user, String password, String database) {}
