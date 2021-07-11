import org.apache.tools.ant.taskdefs.condition.Os

plugins {
  id("acme.java-application-conventions")
  id("acme.java-openjfx-conventions")
  id("org.beryx.jlink")
}

dependencies {
  implementation(project(":hello-world-frontend"))
  implementation(project(":hello-world-backend"))
}

application {
  mainModule.set("com.acme.helloworld")
  mainClass.set("com.acme.helloworld.App")
}

jlink {
  options.addAll(
    "--strip-debug",
    "--compress",
    "2",
    "--no-header-files",
    "--no-man-pages",
    "--include-locales",
    "en,de"
  )
}

if (Os.isFamily(Os.FAMILY_MAC)) {
  jlink {
    jpackage {
      icon = "src/main/macos/AppIcon.icns"
      imageName = "Hello World"
      installerType = "dmg"
      imageOptions = listOf(
        "--copyright", "Copyright © 2020-${extra["copyrightYear"]} ACME Ltd.",
        "--mac-sign",
        "--mac-signing-key-user-name", "Falko Schumann (QC6EN37P56)"
      )
    }
  }
}

if (Os.isFamily(Os.FAMILY_WINDOWS)) {
  jlink {
    jpackage {
      icon = "src/main/win/app.ico"
      imageName = "Hello World"
      imageOptions = listOf(
        "--copyright", "Copyright (c) 2020-${extra["copyrightYear"]} ACME Ltd.",
      )
      installerName = "Hello World"
      installerType = "msi"
      installerOptions = listOf(
        "--copyright", "Copyright (c) 2020-${extra["copyrightYear"]} ACME Ltd.",
        "--license-file", "../LICENSE.txt",
        "--win-dir-chooser",
        "--win-menu",
        "--win-menu-group", "ACME Ltd.",
        "--win-upgrade-uuid", "be60334f-312d-42bd-9cba-cac6d2993dd6"
      )
    }
  }
}
