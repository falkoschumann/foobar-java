module de.muspellheim.helloworld.contract {
  requires static lombok;

  exports de.muspellheim.helloworld.contract;
  exports de.muspellheim.helloworld.contract.data;
  exports de.muspellheim.helloworld.contract.messages.commands;
  exports de.muspellheim.helloworld.contract.messages.queries;
}
