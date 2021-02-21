/*
 * Hello World - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.contract.messages.queries;

import de.muspellheim.helloworld.contract.data.User;
import lombok.Value;

@Value
public class UserQueryResult {
  User user;
}
