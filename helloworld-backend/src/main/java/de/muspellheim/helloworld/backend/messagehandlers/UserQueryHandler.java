/*
 * User Stories - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.backend.messagehandlers;

import de.muspellheim.helloworld.contract.data.User;
import de.muspellheim.helloworld.contract.messages.queries.UserQuery;
import de.muspellheim.helloworld.contract.messages.queries.UserQueryResult;

public class UserQueryHandler {
  public UserQueryResult handle(UserQuery query) {
    return new UserQueryResult(new User(""));
  }
}
