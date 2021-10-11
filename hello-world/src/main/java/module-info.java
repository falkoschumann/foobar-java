module com.acme.helloworld {
  requires static lombok;
  requires javafx.graphics;
  requires java.logging;
  requires com.acme.helloworld.backend;
  requires com.acme.helloworld.frontend;

  opens com.acme.helloworld to
      javafx.graphics;
}
