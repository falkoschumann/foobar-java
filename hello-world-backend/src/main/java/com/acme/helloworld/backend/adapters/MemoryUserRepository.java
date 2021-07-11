/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.data.User;
import java.util.ArrayList;
import java.util.List;

public class MemoryUserRepository implements UserRepository {
  private final List<User> users = new ArrayList<>();

  @Override
  public List<User> findAll() {
    return List.copyOf(users);
  }

  @Override
  public void createUser(User user) {
    users.add(user);
  }
}
