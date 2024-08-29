# Interview task by Daniil Selin (Java 22 is used)

## Pre-requirements

Run `resources/sql/init.sql` script in your local machine (MySQL syntax)

## Build:

`./gradlew bootJar`

## Startup (path/to/props - path to custom application.yml file):

Run from /build/lib directory after build
`java -Dspring.config.location=path/to/props -jar Interview-0.0.1-SNAPSHOT.jar`


## Test mapping for users registration:

You can access `http://localhost:8080/test` mapping from your browser to register random user using HttpClient
