plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}

dependencies {
  implementation("com.diffplug.spotless:spotless-plugin-gradle:5.14.1")
  implementation("com.gluonhq:client-gradle-plugin:0.1.42")
  implementation("io.freefair.gradle:lombok-plugin:6.0.0-m2")
  implementation("org.beryx:badass-jlink-plugin:2.24.0")
  implementation("org.openjfx:javafx-plugin:0.0.10")
}
