module com.acme.foobar.backend {
  requires com.acme.foobar.contract;
  requires java.sql;

  exports com.acme.foobar.backend;
  exports com.acme.foobar.backend.adapters;
  exports com.acme.foobar.backend.messagehandlers;
}
