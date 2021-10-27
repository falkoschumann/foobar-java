module com.acme.helloworld.backend {
  requires static lombok;
  requires java.logging;
  requires java.prefs;
  requires org.apache.commons.csv;
  requires transitive com.acme.helloworld.contract;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
