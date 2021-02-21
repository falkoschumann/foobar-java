/*
 * User Stories - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend;

import de.muspellheim.helloworld.contract.data.User;

public interface UserRepository {
  String create(String name) throws Exception;

  User find() throws Exception;
}
