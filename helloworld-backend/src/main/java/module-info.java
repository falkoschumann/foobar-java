module de.muspellheim.helloworld.backend {
  requires static java.naming;
  requires static lombok;
  requires de.muspellheim.helloworld.contract;
  requires com.google.gson;

  exports de.muspellheim.helloworld.backend;
  exports de.muspellheim.helloworld.backend.adapters;
  exports de.muspellheim.helloworld.backend.messagehandlers;
}
