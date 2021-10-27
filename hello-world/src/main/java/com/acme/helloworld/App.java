/*
 * Hello World - Application
 * Copyright (c) 2020 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import com.acme.helloworld.backend.EventStore;
import com.acme.helloworld.backend.LoggingMessageHandler;
import com.acme.helloworld.backend.MessageHandler;
import com.acme.helloworld.backend.PreferencesRepository;
import com.acme.helloworld.backend.adapters.CsvEventStore;
import com.acme.helloworld.backend.adapters.MemoryEventStore;
import com.acme.helloworld.backend.adapters.MemoryPreferencesRepository;
import com.acme.helloworld.backend.adapters.PrefsPreferencesRepository;
import com.acme.helloworld.frontend.MainWindowController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private EventStore eventStore;
  private PreferencesRepository preferencesRepository;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void init() {
    if (startAsDemo()) {
      initDemo();
    } else {
      initDefault();
    }
  }

  private boolean startAsDemo() {
    return getParameters().getUnnamed().contains("--demo");
  }

  private void initDefault() {
    eventStore = new CsvEventStore();
    preferencesRepository = new PrefsPreferencesRepository();
  }

  private void initDemo() {
    eventStore = new MemoryEventStore().addExamples();
    preferencesRepository = new MemoryPreferencesRepository().addExamples();
  }

  @Override
  public void start(Stage primaryStage) {
    var backend = new LoggingMessageHandler(new MessageHandler(eventStore, preferencesRepository));
    var frontend = MainWindowController.create(primaryStage, backend);
    frontend.run();
  }
}
