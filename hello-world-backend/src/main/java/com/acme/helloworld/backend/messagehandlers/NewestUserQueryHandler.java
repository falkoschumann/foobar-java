/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.EventStore;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import com.acme.helloworld.contract.data.User;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;

public class NewestUserQueryHandler {
  private User newestUser;

  public NewestUserQueryHandler(EventStore eventStore) {
    newestUser =
        eventStore
            .replay(UserCreatedEvent.class)
            .reduce((first, second) -> second)
            .map(it -> new User(it.id(), it.name()))
            .orElse(null);

    eventStore.addRecordedObserver(
        it -> {
          if (it instanceof UserCreatedEvent e) {
            newestUser = new User(e.id(), e.name());
          }
        });
  }

  public NewestUserQueryResult handle(NewestUserQuery query) {
    return new NewestUserQueryResult(newestUser);
  }
}
