plugins {
  id("acme.java-library-conventions")
  id("acme.java-postgres-conventions")
  id("acme.java-modules-conventions")
}

dependencies {
  api(project(":hello-world-contract"))
  implementation("org.apache.commons:commons-csv:1.8")
}
