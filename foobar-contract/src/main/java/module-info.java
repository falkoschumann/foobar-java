module com.acme.foobar.contract {
  requires static lombok;

  exports com.acme.foobar.contract;
  exports com.acme.foobar.contract.data;
  exports com.acme.foobar.contract.messages.commands;
  exports com.acme.foobar.contract.messages.queries;
}
