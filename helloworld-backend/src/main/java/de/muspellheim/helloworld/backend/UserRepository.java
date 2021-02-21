/*
 * Hello World - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend;

import de.muspellheim.helloworld.contract.data.User;

public interface UserRepository {
  void setCurrentUser(User user) throws Exception;

  User getCurrentUser() throws Exception;
}
