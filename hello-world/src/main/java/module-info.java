module com.acme.helloworld {
  requires com.acme.helloworld.contract;
  requires com.acme.helloworld.backend;
  requires com.acme.helloworld.frontend;
  requires javafx.graphics;

  exports com.acme.helloworld;
}
