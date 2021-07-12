plugins {
  id("acme.java-library-conventions")
  id("acme.java-postgres-conventions")
}

dependencies {
  api(project(":hello-world-contract"))
}
