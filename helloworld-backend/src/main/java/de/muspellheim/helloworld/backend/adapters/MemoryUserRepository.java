/*
 * Hello World - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.adapters;

import de.muspellheim.helloworld.backend.UserRepository;
import de.muspellheim.helloworld.contract.data.User;

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
