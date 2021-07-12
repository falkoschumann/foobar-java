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

  public void initWithTestdata() {
    users.add(new User("0dc31588-bda7-4987-adc5-ad4413fc3e54", "Alice"));
  }

  @Override
  public List<User> findAll() {
    return List.copyOf(users);
  }

  @Override
  public void createUser(User user) {
    users.add(user);
  }
}
