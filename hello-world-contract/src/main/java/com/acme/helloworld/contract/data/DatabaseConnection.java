/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.data;

public record DatabaseConnection(
    boolean useDefaults, String host, int port, String user, String password, String database) {}
