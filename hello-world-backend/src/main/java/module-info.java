module com.acme.foobar.backend {
  requires com.acme.foobar.contract;
  requires java.sql;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
