module com.acme.helloworld {
  requires com.acme.helloworld.backend;
  requires com.acme.helloworld.frontend;
  requires javafx.graphics;

  opens com.acme.helloworld to
      javafx.graphics;
}
