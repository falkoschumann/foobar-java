module com.acme.helloworld.backend {
  requires static lombok;
  requires transitive com.acme.helloworld.contract;
  requires org.postgresql.jdbc;
  requires java.naming;
  requires java.prefs;
  requires java.sql;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
