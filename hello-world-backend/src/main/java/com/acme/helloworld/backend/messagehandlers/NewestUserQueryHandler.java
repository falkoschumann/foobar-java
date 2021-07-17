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
  // TODO Nutze Replay nur initial und dann Recorded Observer
  private final EventStore eventStore;

  public NewestUserQueryHandler(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  public NewestUserQueryResult handle(NewestUserQuery query) {
    var events = eventStore.replay(UserCreatedEvent.class).toList();
    if (events.isEmpty()) {
      return new NewestUserQueryResult(null);
    }

    var lastEvent = events.get(events.size() - 1);
    var user = new User(lastEvent.id(), lastEvent.name());
    return new NewestUserQueryResult(user);
  }
}
