/*
 * Hello World - Backend
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.backend;

import com.acme.helloworld.contract.data.User;
import java.util.List;

public interface UserRepository {
  List<User> findAll();

  void createUser(User user);
}
