import org.apache.tools.ant.taskdefs.condition.Os

plugins {
  application
  id("org.beryx.jlink")
  id("acme.java-common-conventions")
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
      imageOptions.addAll(
        listOf(
          "--copyright", "Copyright © 2020-${extra["copyrightYear"]} ACME Ltd.",
          "--mac-sign",
          "--mac-signing-key-user-name", "Falko Schumann (QC6EN37P56)"
        )
      )
      installerType = "dmg"
    }
  }
}

if (Os.isFamily(Os.FAMILY_WINDOWS)) {
  jlink {
    jpackage {
      icon = "src/main/win/app.ico"
      imageOptions = listOf(
        "--copyright", "Copyright (c) 2020-${extra["copyrightYear"]} ACME Ltd.",
      )
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
