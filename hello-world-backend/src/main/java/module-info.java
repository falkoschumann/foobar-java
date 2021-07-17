module com.acme.helloworld.backend {
  requires transitive com.acme.helloworld.contract;
  requires static lombok;
  requires org.apache.commons.csv;
  requires java.prefs;

  exports com.acme.helloworld.backend;
  exports com.acme.helloworld.backend.adapters;
  exports com.acme.helloworld.backend.messagehandlers;
}
