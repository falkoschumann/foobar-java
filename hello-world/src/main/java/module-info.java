module com.acme.helloworld {
  requires javafx.graphics;
  requires com.acme.helloworld.backend;
  requires com.acme.helloworld.frontend;

  opens com.acme.helloworld to
      javafx.graphics;
}
