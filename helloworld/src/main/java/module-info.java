module de.muspellheim.helloworld {
  requires static lombok;
  requires de.muspellheim.helloworld.backend;
  requires de.muspellheim.helloworld.contract;
  requires de.muspellheim.helloworld.frontend;
  requires javafx.graphics;

  exports de.muspellheim.helloworld;
}
