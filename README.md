# Password Manager

This is a backend application using Spring Boot to store passwords of web applications. Basically the same as Lastpass
or Dashlane.

## Development

- in the root folder, run `docker compose up` -> That will spin up the PostreSQL on port 5438.
- run `mvn clean install` -> To install the dependencies.
- run the main class using some IDE or run `mvn spring-boot:run` -> That will run the application on port 8080.

## PostgreSQL

It's important to create an `.env` file with the env var `POSTGRESS_PASSWORD`. That is required by the Docker image.
Just copy the `.env.example` and rename it to `.env`.

## Spring Boot

It's all pretty much configured so nothing to add here *yet*.
