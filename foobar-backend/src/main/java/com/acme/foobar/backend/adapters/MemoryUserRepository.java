/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.adapters;

import com.acme.foobar.backend.UserRepository;
import com.acme.foobar.contract.data.User;

public class MemoryUserRepository implements UserRepository {
  private User currentUser;

  @Override
  public void setCurrentUser(User user) {
    currentUser = user;
  }

  @Override
  public User getCurrentUser() {
    return currentUser;
  }
}
