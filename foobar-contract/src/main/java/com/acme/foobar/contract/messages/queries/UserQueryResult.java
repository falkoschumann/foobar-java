/*
 * Foobar - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.contract.messages.queries;

import com.acme.foobar.contract.data.User;
import lombok.Value;

@Value
public class UserQueryResult {
  User user;
}
