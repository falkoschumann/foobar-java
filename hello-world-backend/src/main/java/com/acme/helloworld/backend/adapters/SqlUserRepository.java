/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import com.acme.helloworld.backend.UserRepository;
import com.acme.helloworld.contract.data.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlUserRepository implements UserRepository {
  private final DataSource dataSource;

  public SqlUserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<User> findAll() {
    var sql = """
      SELECT id, name,
      FROM users
      ORDER BY name
      """;
    try (var connection = dataSource.getConnection()) {
      try (var statement = connection.prepareStatement(sql)) {
        var resultSet = statement.executeQuery();
        return mapUsers(resultSet);
      }
    } catch (SQLException e) {
      throw new UncheckedSQLException("Error finding all users", e);
    }
  }

  @Override
  public Optional<User> findById(String id) {
    var sql = """
      SELECT id, name,
      FROM users
      WHERE id = ?
      """;
    try (var connection = dataSource.getConnection()) {
      try (var statement = connection.prepareStatement(sql)) {
        statement.setString(1, id);
        var resultSet = statement.executeQuery();
        if (resultSet.next()) {
          return Optional.of(mapUser(resultSet));
        } else {
          return Optional.empty();
        }
      }
    } catch (SQLException e) {
      throw new UncheckedSQLException("Error finding user by id", e);
    }
  }

  private static List<User> mapUsers(ResultSet resultSet) throws SQLException {
    var list = new ArrayList<User>();
    while (resultSet.next()) {
      list.add(mapUser(resultSet));
    }
    return List.copyOf(list);
  }

  private static User mapUser(ResultSet resultSet) throws SQLException {
    return new User(resultSet.getString("id"), resultSet.getString("name"));
  }

  @Override
  public void createUser(User user) {
    var sql = """
      INSERT INTO users (id, name)
      VALUES (?, ?)
      """;
    try (var connection = dataSource.getConnection()) {
      try (var statement = connection.prepareStatement(sql)) {
        statement.setString(1, user.id());
        statement.setString(2, user.name());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      throw new UncheckedSQLException("Error creating user", e);
    }
  }
}
