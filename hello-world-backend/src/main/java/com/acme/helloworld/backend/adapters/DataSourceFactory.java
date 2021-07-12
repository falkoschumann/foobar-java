/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;

public final class DataSourceFactory {
  private DataSourceFactory() {}

  public static DataSource createDataSource(
      String host, int port, String user, String password, String database) {
    var ds = new PGSimpleDataSource();
    ds.setServerNames(new String[] {host});
    ds.setPortNumbers(new int[] {port});
    ds.setUser(user);
    ds.setPassword(password);
    ds.setDatabaseName(database);
    return ds;
  }
}
