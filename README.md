![Java CI](https://github.com/falkoschumann/foobar-java/workflows/Java%20CI/badge.svg)

# Foobar

TODO: Description

## Installation

### macOS

Die DMG-Datei öffnen und die App in den Ordner _Programme_ ziehen. Die App kann
anschließend aus dem _Programme_-Ordern gestartet werden.

### Windows

Die MSI-Datei ausführen. Die App kann anschließend über das Startmenü gestartet
werden.

## Usage

TODO: Usage

## Contributing

- Der Code Style [Google Java Style Guide][1] wird beim Build geprüft.
- Code formatieren: `./gradlew spotlessApply`
- Release erstellen: `./gradlew jpackage`
- [Project Lombok][2] wird verwendet, um Boilerplate Code zu reduzieren, es
  werden nur stabile Features verwendet, zum Beispiel: `@Getter` oder `@Setter`.

### Distribute for macOS

Im Folgenden müssen `$MAC_SIGNING_USERNAME`, `$MAC_SIGNING_PASSWORD`
und `{RequestUUID}` passend ersetzt werden.

1. Notarisierung der App-Distribution beantragen:

   `xcrun altool --notarize-app --primary-bundle-id com.acme.helloworld --username $MAC_SIGNING_USERNAME --password $MAC_SIGNING_PASSWORD --file foobar/build/jpackage/foobar-1.1.0.dmg`

2. Status der Notarisierung prüfen:

   `xcrun altool --notarization-info {RequestUUID} --username $MAC_SIGNING_USERNAME --password $MAC_SIGNING_PASSWORD`

3. Wenn Notarisierung beglaubigt, App-Distribution um Information zur
   Notarisierung ergänzen:

   `xcrun stapler staple foobar/build/jpackage/foobar-1.1.0.dmg`


[1]: https://google.github.io/styleguide/javaguide.html
[2]: https://projectlombok.org
