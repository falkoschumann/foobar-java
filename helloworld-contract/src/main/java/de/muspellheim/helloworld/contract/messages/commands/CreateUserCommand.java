/*
 * User Stories - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.helloworld.contract.messages.commands;

import lombok.Value;

@Value
public class CreateUserCommand {
  String name;
}
