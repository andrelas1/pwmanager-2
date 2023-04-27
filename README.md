# Password Manager

This is a backend application using Spring Boot to store passwords of web applications. Basically the same as Lastpass
or Dashlane.

## Development

- in the root folder, run `docker compose up` -> That will spin up the PostreSQL on port 5438.
- run `mvn clean install` -> To install the dependencies.
- run the main class using some IDE or run `mvn spring-boot:run` -> That will run the application on port 8080.

In Intellij IDEA, set the run configuration as Maven and activate the `local` profile. That will pre-populate the DB with some values.

### Hot Reload
This project contains the `spring-boot-devtools` package which helps with hot reloading when a file in the classpath changed. Some IDE's like IDEA work quite well but specially in the paid version. For free version, I added a shell script that can be configured in the IDE to run everytime a Java file changes. That script will rebuild the project while skipping tests and the `devtools` will re-run the application when those changes take effect.

IDEA ultimate does not need that as we can use the Spring run configuration in there. But the Community version does not have such a feature, so the shelel script comes in handy.

## PostgreSQL

It's important to create an `.env` file with the env var `POSTGRESS_PASSWORD`. That is required by the Docker image.
Just copy the `.env.example` and rename it to `.env`.

## Spring Boot

It's all pretty much configured so nothing to add here *yet*.
