/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.Event;
import com.acme.helloworld.backend.EventStore;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public abstract class AbstractEventStore implements EventStore {
  private final List<Consumer<Event>> recordedObserver = new CopyOnWriteArrayList<>();

  @Override
  public void addRecordedObserver(Consumer<Event> observer) {
    recordedObserver.add(observer);
  }

  @Override
  public void removeRecordedObserver(Consumer<Event> observer) {
    recordedObserver.remove(observer);
  }

  protected void notifyRecordedObservers(Event event) {
    recordedObserver.forEach(it -> it.accept(event));
  }
}
