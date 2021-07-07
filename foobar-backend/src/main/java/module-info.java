module com.acme.foobar.backend {
  requires static java.naming;
  requires static lombok;
  requires com.acme.foobar.contract;
  requires com.google.gson;

  exports com.acme.foobar.backend;
  exports com.acme.foobar.backend.adapters;
  exports com.acme.foobar.backend.messagehandlers;
}
