/*
 * User Stories - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.adapters;

import de.muspellheim.helloworld.backend.UserRepository;
import de.muspellheim.helloworld.contract.data.User;

public class MemoryUserRepository implements UserRepository {
  private User user;

  @Override
  public String create(String name) throws Exception {
    user = new User(name);
    return user.getId();
  }

  @Override
  public User find() throws Exception {
    return user;
  }
}
