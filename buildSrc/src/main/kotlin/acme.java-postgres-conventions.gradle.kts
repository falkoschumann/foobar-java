plugins {
  java
  id("com.nocwriter.runsql")
}

dependencies {
  implementation("org.postgresql:postgresql:42.2.22")
}

ext {
  if (!project.hasProperty("databaseHost")) {
    if (System.getenv("CI") != null) {
      set("databaseHost", "postgres")
    } else {
      set("databaseHost", "localhost")
    }
  }
  if (!project.hasProperty("databasePort")) {
    set("databasePort", "5432")
  }
  if (!project.hasProperty("databaseName")) {
    set("databaseName", "acme_test")
  }
  if (!project.hasProperty("databaseUser")) {
    set("databaseUser", "acme_test")
  }
  if (!project.hasProperty("databasePassword")) {
    set("databasePassword", "acme_test")
  }
}

tasks.register<RunSQL>("createSchema") {
  group = "database"
  println("databaseUser: " + ext["databaseUser"])
  println("databasePassword: " + ext["databasePassword"])
  println("databaseHost: " + ext["databaseHost"])
  println("databasePort: " + ext["databasePort"])
  println("databaseName: " + ext["databaseName"])
  config {
    username = ext["databaseUser"] as String
    password = ext["databasePassword"] as String
    url =
      "jdbc:postgresql://" + ext["databaseHost"] + ":" + ext["databasePort"] + "/" + ext["databaseName"]
    driverClassName = "org.postgresql.Driver"
    scriptFile = "src/test/sql/schema.sql"
  }
}

tasks.register<RunSQL>("insertTestdata") {
  group = "database"
  config {
    username = ext["databaseUser"] as String
    password = ext["databasePassword"] as String
    url =
      "jdbc:postgresql://" + ext["databaseHost"] + ":" + ext["databasePort"] + "/" + ext["databaseName"]
    driverClassName = "org.postgresql.Driver"
    scriptFile = "src/test/sql/insert-testdata.sql"
  }
}

tasks.register<RunSQL>("deleteTestdata") {
  group = "database"
  config {
    username = ext["databaseUser"] as String
    password = ext["databasePassword"] as String
    url =
      "jdbc:postgresql://" + ext["databaseHost"] + ":" + ext["databasePort"] + "/" + ext["databaseName"]
    driverClassName = "org.postgresql.Driver"
    scriptFile = "src/test/sql/delete-testdata.sql"
  }
}
