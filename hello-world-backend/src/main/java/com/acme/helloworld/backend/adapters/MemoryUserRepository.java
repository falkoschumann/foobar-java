/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.data.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
  private final List<User> users = new ArrayList<>();

  @Override
  public List<User> findAll() {
    return List.copyOf(users);
  }

  @Override
  public Optional<User> findById(String id) {
    return users.stream().filter(it -> it.id().equals(id)).findAny();
  }

  @Override
  public void createUser(User user) {
    users.add(user);
  }
}
