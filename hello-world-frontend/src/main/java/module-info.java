module com.acme.helloworld.frontend {
  requires static lombok;
  requires com.acme.helloworld.contract;
  requires javafx.controls;
  requires javafx.fxml;
  requires jdk.localedata;

  exports com.acme.helloworld.frontend;

  opens com.acme.helloworld.frontend to
      javafx.fxml;
}
