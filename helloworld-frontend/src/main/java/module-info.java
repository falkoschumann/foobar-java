module de.muspellheim.helloworld.frontend {
  requires static lombok;
  requires de.muspellheim.helloworld.contract;
  requires javafx.controls;
  requires javafx.fxml;

  exports de.muspellheim.helloworld.frontend;

  opens de.muspellheim.helloworld.frontend;
}
