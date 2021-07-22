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
import com.acme.helloworld.backend.messagehandlers.ChangeMainWindowBoundsCommandHandler;
import com.acme.helloworld.backend.messagehandlers.ChangePreferencesCommandHandler;
import com.acme.helloworld.backend.messagehandlers.CreateUserCommandHandler;
import com.acme.helloworld.backend.messagehandlers.MainWindowBoundsQueryHandler;
import com.acme.helloworld.backend.messagehandlers.NewestUserQueryHandler;
import com.acme.helloworld.backend.messagehandlers.PreferencesQueryHandler;
import com.acme.helloworld.contract.messages.commands.Failure;
import com.acme.helloworld.contract.messages.queries.NewestUserQuery;
import com.acme.helloworld.contract.messages.queries.PreferencesQuery;
import com.acme.helloworld.frontend.MainWindowController;
import java.util.concurrent.CompletableFuture;
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
    var changeMainWindowBoundsCommandHandler =
        new ChangeMainWindowBoundsCommandHandler(preferencesRepository);
    var changePreferencesCommandHandler =
        new ChangePreferencesCommandHandler(preferencesRepository);
    var createUserCommandHandler = new CreateUserCommandHandler(eventStore);
    var mainWindowBoundsQueryHandler = new MainWindowBoundsQueryHandler(preferencesRepository);
    var newestUserQueryHandler = new NewestUserQueryHandler(eventStore);
    var preferencesQueryHandler = new PreferencesQueryHandler(preferencesRepository);
    var frontend = MainWindowController.create(primaryStage);

    frontend.setOnChangeMainWindowBoundsCommand(
        command ->
            CompletableFuture.supplyAsync(
                    () -> changeMainWindowBoundsCommandHandler.handle(command))
                .thenAcceptAsync(
                    status -> {
                      if (status instanceof Failure failure) {
                        frontend.display(failure);
                      }
                    },
                    Platform::runLater));
    frontend.setOnChangePreferencesCommand(
        command ->
            CompletableFuture.supplyAsync(() -> changePreferencesCommandHandler.handle(command))
                .thenAcceptAsync(
                    status -> {
                      if (status instanceof Failure failure) {
                        frontend.display(failure);
                      } else {
                        var result = preferencesQueryHandler.handle(new PreferencesQuery());
                        frontend.display(result);
                      }
                    },
                    Platform::runLater));
    frontend.setOnCreateUserCommand(
        command ->
            CompletableFuture.supplyAsync(() -> createUserCommandHandler.handle(command))
                .thenAcceptAsync(
                    status -> {
                      if (status instanceof Failure failure) {
                        frontend.display(failure);
                      } else {
                        var result = newestUserQueryHandler.handle(new NewestUserQuery());
                        frontend.display(result);
                      }
                    },
                    Platform::runLater));
    frontend.setOnMainWindowBoundsQuery(
        query ->
            CompletableFuture.supplyAsync(() -> mainWindowBoundsQueryHandler.handle(query))
                .thenAcceptAsync(frontend::display, Platform::runLater));
    frontend.setOnNewestUserQuery(
        query ->
            CompletableFuture.supplyAsync(() -> newestUserQueryHandler.handle(query))
                .thenAcceptAsync(frontend::display, Platform::runLater));
    frontend.setOnPreferencesQuery(
        query ->
            CompletableFuture.supplyAsync(() -> preferencesQueryHandler.handle(query))
                .thenAcceptAsync(frontend::display, Platform::runLater));

    frontend.run();
  }
}
