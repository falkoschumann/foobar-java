module de.muspellheim.helloworld.contract {
  requires static lombok;

  exports de.muspellheim.helloworld.contract;
  exports de.muspellheim.helloworld.contract.data;
  exports de.muspellheim.helloworld.contract.messages.commands;
  exports de.muspellheim.helloworld.contract.messages.queries;

  opens de.muspellheim.helloworld.contract.data;
  opens de.muspellheim.helloworld.contract.messages.commands;
  opens de.muspellheim.helloworld.contract.messages.queries;
}
