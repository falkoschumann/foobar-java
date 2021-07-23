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
        commandProcessor(
            changeMainWindowBoundsCommandHandler::handle, App::noOperation, frontend::display));
    var preferencesQueryProcessor =
        queryProcessor(preferencesQueryHandler::handle, frontend::display);
    frontend.setOnChangePreferencesCommand(
        commandProcessor(
            changePreferencesCommandHandler::handle,
            () -> preferencesQueryProcessor.accept(new PreferencesQuery()),
            frontend::display));
    var newestUserQueryProcessor =
        queryProcessor(newestUserQueryHandler::handle, frontend::display);
    frontend.setOnCreateUserCommand(
        commandProcessor(
            createUserCommandHandler::handle,
            () -> newestUserQueryProcessor.accept(new NewestUserQuery()),
            frontend::display));
    frontend.setOnMainWindowBoundsQuery(
        queryProcessor(mainWindowBoundsQueryHandler::handle, frontend::display));
    frontend.setOnNewestUserQuery(newestUserQueryProcessor);
    frontend.setOnPreferencesQuery(preferencesQueryProcessor);

    frontend.run();
  }

  private static <C, S> Consumer<C> commandProcessor(
      Function<C, S> commandHandler, Runnable onSuccess, Consumer<Failure> onFailure) {
    return command ->
        CompletableFuture.supplyAsync(() -> commandHandler.apply(command))
            .thenAcceptAsync(
                status -> {
                  if (status instanceof Failure failure) {
                    onFailure.accept(failure);
                  } else {
                    onSuccess.run();
                  }
                },
                Platform::runLater);
  }

  private static void noOperation() {}

  private static <Q, R> Consumer<Q> queryProcessor(
      Function<Q, R> queryHandler, Consumer<R> projector) {
    return query ->
        CompletableFuture.supplyAsync(() -> queryHandler.apply(query))
            .thenAcceptAsync(projector, Platform::runLater);
  }
}
