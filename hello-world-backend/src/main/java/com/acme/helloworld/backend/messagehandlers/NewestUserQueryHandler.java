/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.EventStore;
import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.backend.events.UserCreatedEvent;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.NewestUserQueryResult;

public class NewestUserQueryHandler {
  private final PreferencesRepository preferencesRepository;

  private String newestUser;

  public NewestUserQueryHandler(
      EventStore eventStore, PreferencesRepository preferencesRepository) {
    this.preferencesRepository = preferencesRepository;
    replay(eventStore);
    hookListener(eventStore);
  }

  private void replay(EventStore eventStore) {
    newestUser =
        eventStore
            .replay(UserCreatedEvent.class)
            .reduce((first, second) -> second)
            .map(UserCreatedEvent::name)
            .orElse(null);
  }

  private void hookListener(EventStore eventStore) {
    eventStore.addRecordedObserver(
        it -> {
          if (it instanceof UserCreatedEvent e) {
            newestUser = e.name();
          }
        });
  }

  public NewestUserQueryResult handle(NewestUserQuery query) {
    var greeting = preferencesRepository.loadGreeting();
    greeting = greeting.replace("$user", newestUser);
    return new NewestUserQueryResult(greeting);
  }
}
