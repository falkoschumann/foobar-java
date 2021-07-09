module com.acme.foobar {
  requires static lombok;
  requires com.acme.foobar.backend;
  requires com.acme.foobar.contract;
  requires com.acme.foobar.frontend;
  requires javafx.graphics;
  requires javafx.controls;

  exports com.acme.helloworld;
}
