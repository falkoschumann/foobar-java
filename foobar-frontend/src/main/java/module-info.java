module com.acme.foobar.frontend {
  requires static lombok;
  requires com.acme.foobar.contract;
  requires javafx.controls;
  requires javafx.fxml;

  exports com.acme.foobar.frontend;

  opens com.acme.foobar.frontend to
      javafx.fxml;
}
