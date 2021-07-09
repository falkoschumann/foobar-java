plugins {
  id("acme.java-library-conventions")
  id("acme.java-openjfx-conventions")
}

dependencies {
  api(project(":hello-world-contract"))
}
