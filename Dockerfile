# Build stage
FROM maven:3-openjdk-17-slim AS build
WORKDIR /app
COPY src src
COPY pom.xml .
RUN mvn -f pom.xml -Pproduction clean package



WORKDIR /app
COPY --from=build /app/target/camelpro-1.0.0.jar camelpro.jar
ENTRYPOINT ["java","-jar","camelpro.jar"]