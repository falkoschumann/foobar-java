/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend.adapters;

import javax.sql.DataSource;

public class TestDataSourceFactory {
  public static DataSource createDataSource() {
    var host = System.getenv("GITLAB_CI") != null ? "postgres" : "localhost";
    return DataSourceFactory.createDataSource(host, 5432, "acme_test", "acme_test", "acme_test");
  }
}
