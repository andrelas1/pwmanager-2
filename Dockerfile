FROM eclipse-temurin:17

EXPOSE 8080

ENV DATASOURCE_URL $DATASOURCE_URL
ENV POSTGRES_PASSWORD $POSTGRES_PASSWORD
ENV POSTGRES_USERNAME $POSTGRES_USERNAME

RUN mkdir /app

COPY target/*.jar /app/spring-boot-application.jar
COPY src/main/resources/application.properties /app/config/application-production.properties


ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app/spring-boot-application.jar", "--spring.config.location=file:/app/config/application-production.properties"]



HEALTHCHECK --interval=1m --timeout=3s CMD wget -q -T 3 -s http://localhost:8080/actuator/health/ || exit 1
