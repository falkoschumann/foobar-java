module com.acme.helloworld.backend {
  requires java.logging;
  requires java.prefs;
  requires com.google.gson;
  requires static lombok;
  requires org.apache.commons.csv;
  requires transitive com.acme.helloworld.contract;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
