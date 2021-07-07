/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend;

import com.acme.foobar.contract.data.User;

public interface UserRepository {
  void setCurrentUser(User user) throws Exception;

  User getCurrentUser() throws Exception;
}
