module com.acme.helloworld.backend {
  requires com.acme.helloworld.contract;
  requires java.sql;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
