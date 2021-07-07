import org.apache.tools.ant.taskdefs.condition.Os

plugins {
  id("acme.java-application-conventions")
  id("acme.java-openjfx-conventions")
  id("com.gluonhq.client-gradle-plugin")
  id("org.beryx.jlink")
}

dependencies {
  implementation(project(":foobar-frontend"))
  implementation(project(":foobar-backend"))
}

application {
  mainModule.set("com.acme.foobar")
  mainClass.set("com.acme.foobar.App")
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
      imageName = "Foobar"
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
      imageName = "Foobar"
      imageOptions = listOf(
        "--copyright", "Copyright (c) 2020-${extra["copyrightYear"]} ACME Ltd.",
      )
      installerName = "Foobar"
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
