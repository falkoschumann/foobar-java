package com.acme.helloworld.backend.messagehandlers;

import com.acme.helloworld.backend.adapters.DataSourceFactory;
import com.acme.helloworld.contract.messages.commands.CommandStatus;
import com.acme.helloworld.contract.messages.commands.Success;
import com.acme.helloworld.contract.messages.commands.TestDatabaseConnectionCommand;
import com.acme.helloworld.contract.messages.notifications.DatabaseConnectionFaultyNotification;
import com.acme.helloworld.contract.messages.notifications.DatabaseConnectionSuccessfulNotification;
import java.sql.SQLException;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

public class TestDatabaseConnectionCommandHandler {
  @Getter @Setter
  private Consumer<DatabaseConnectionFaultyNotification> onDatabaseConnectionFaultyNotification;

  @Getter @Setter
  private Consumer<DatabaseConnectionSuccessfulNotification>
      onDatabaseConnectionSuccessfulNotification;

  public CommandStatus handle(TestDatabaseConnectionCommand command) {
    var dataSource =
        DataSourceFactory.createDataSource(
            command.host(), command.port(), command.user(), command.password(), command.database());
    try (var ignored = dataSource.getConnection()) {
      onDatabaseConnectionSuccessfulNotification.accept(
          new DatabaseConnectionSuccessfulNotification());
    } catch (SQLException e) {
      onDatabaseConnectionFaultyNotification.accept(
          new DatabaseConnectionFaultyNotification(e.getLocalizedMessage()));
    }
    return new Success();
  }
}
