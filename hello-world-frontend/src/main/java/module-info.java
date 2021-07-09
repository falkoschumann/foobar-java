module com.acme.foobar.frontend {
  requires static lombok;
  requires com.acme.foobar.contract;
  requires javafx.controls;
  requires javafx.fxml;

  exports com.acme.helloworld.frontend;

  opens com.acme.helloworld.frontend to
      javafx.fxml;
}
