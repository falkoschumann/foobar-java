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
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {
  private EventStore eventStore;
  private PreferencesRepository preferencesRepository;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void init() {
    if (getParameters().getUnnamed().contains("--demo")) {
      eventStore = new MemoryEventStore().addExamples();
      preferencesRepository = new MemoryPreferencesRepository().addExamples();
    } else {
      eventStore = new CsvEventStore();
      preferencesRepository = new PrefsPreferencesRepository();
    }
  }

  @Override
  public void start(Stage primaryStage) {
    var backend = new LoggingMessageHandler(new MessageHandler(eventStore, preferencesRepository));
    var frontend = MainWindowController.create(primaryStage, backend);
    frontend.run();
  }

  @Deprecated
  private static <I> Consumer<I> handle(Consumer<I> handler, Consumer<Throwable> onError) {
    return request ->
        CompletableFuture.runAsync(() -> handler.accept(request))
            .exceptionallyAsync(
                exception -> {
                  onError.accept(exception);
                  return null;
                },
                Platform::runLater);
  }

  @Deprecated
  private static <I, O> Consumer<I> handle(
      Function<I, O> handler, Consumer<O> onSuccess, Consumer<Throwable> onError) {
    return request ->
        CompletableFuture.supplyAsync(() -> handler.apply(request))
            .whenCompleteAsync(
                (response, exception) -> {
                  if (response != null) {
                    onSuccess.accept(response);
                  } else if (exception != null) {
                    onError.accept(exception);
                  }
                },
                Platform::runLater);
  }
}
