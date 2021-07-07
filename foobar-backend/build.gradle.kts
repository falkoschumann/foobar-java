plugins {
  id("acme.java-library-conventions")
}

dependencies {
  api(project(":foobar-contract"))
  implementation("com.google.code.gson:gson:2.8.6")
}
