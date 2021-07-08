/*
 * Foobar - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.foobar.backend.adapters;

import com.acme.foobar.contract.data.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class SqlRepository {
  private final DataSource dataSource;

  public SqlRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

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
      throw new UncheckedSQLException(e);
    }
  }

  public User findById(String id) {
    var sql = """
      SELECT id, name,
      FROM users
      WHERE id = ?
      """;
    try (var connection = dataSource.getConnection()) {
      try (var statement = connection.prepareStatement(sql)) {
        statement.setString(1, id);
        var resultSet = statement.executeQuery();
        return mapUser(resultSet);
      }
    } catch (SQLException e) {
      throw new UncheckedSQLException(e);
    }
  }

  private List<User> mapUsers(ResultSet resultSet) throws SQLException {
    var list = new ArrayList<User>();
    while (resultSet.next()) {
      list.add(mapUser(resultSet));
    }
    return List.copyOf(list);
  }

  private User mapUser(ResultSet resultSet) throws SQLException {
    return new User(resultSet.getString("id"), resultSet.getString("name"));
  }
}
