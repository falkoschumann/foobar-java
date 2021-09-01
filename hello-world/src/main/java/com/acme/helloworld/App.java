/*
 * Hello World - Application
 * Copyright (c) 2020 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld;

import com.acme.helloworld.backend.EventStore;
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
    var requestHandler = new RequestHandler(eventStore, preferencesRepository);
    var frontend = MainWindowController.create(primaryStage);

    frontend.setOnChangeMainWindowBoundsCommand(handle(requestHandler::handle, frontend::display));
    frontend.setOnChangePreferencesCommand(
        handle(requestHandler::handle, frontend::display, frontend::display));
    frontend.setOnCreateUserCommand(
        handle(requestHandler::handle, frontend::display, frontend::display));
    frontend.setOnMainWindowBoundsQuery(
        handle(requestHandler::handle, frontend::display, frontend::display));
    frontend.setOnNewestUserQuery(
        handle(requestHandler::handle, frontend::display, frontend::display));
    frontend.setOnPreferencesQuery(
        handle(requestHandler::handle, frontend::display, frontend::display));

    frontend.run();
  }

  private static <I> Consumer<I> handle(
      Function<I, CompletableFuture<Void>> handler, Consumer<Throwable> onError) {
    return request ->
        handler
            .apply(request)
            .exceptionallyAsync(
                exception -> {
                  onError.accept(exception);
                  return null;
                },
                Platform::runLater);
  }

  private static <I, O> Consumer<I> handle(
      Function<I, CompletableFuture<O>> handler,
      Consumer<O> onSuccess,
      Consumer<Throwable> onError) {
    return request ->
        handler
            .apply(request)
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
