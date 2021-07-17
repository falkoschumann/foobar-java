/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

import java.time.Instant;

public interface Event {
  String id();

  Instant timestamp();
}
