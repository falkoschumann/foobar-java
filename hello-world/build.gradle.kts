import org.apache.tools.ant.taskdefs.condition.Os

plugins {
  id("acme.java-application-conventions")
  id("acme.java-openjfx-conventions")
}

dependencies {
  implementation(project(":hello-world-frontend"))
  implementation(project(":hello-world-backend"))
}

application {
  mainModule.set("com.acme.helloworld")
  mainClass.set("com.acme.helloworld.App")
}

if (Os.isFamily(Os.FAMILY_MAC)) {
  jlink {
    jpackage {
      imageName = "Hello World"
    }
  }
}

if (Os.isFamily(Os.FAMILY_WINDOWS)) {
  jlink {
    jpackage {
      imageName = "Hello World"
      installerName = "Hello World"
    }
  }
}
