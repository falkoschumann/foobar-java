module com.acme.helloworld.frontend {
  requires javafx.controls;
  requires javafx.fxml;
  requires jdk.localedata;
  requires static lombok;
  requires transitive com.acme.helloworld.contract;

  exports com.acme.helloworld.frontend;

  opens com.acme.helloworld.frontend to
      javafx.fxml;
}
