module com.acme.helloworld.frontend {
  requires static lombok;
  requires javafx.controls;
  requires javafx.fxml;
  requires jdk.localedata;
  requires transitive com.acme.helloworld.contract;

  exports com.acme.helloworld.frontend;

  opens com.acme.helloworld.frontend to
      javafx.fxml;
}
