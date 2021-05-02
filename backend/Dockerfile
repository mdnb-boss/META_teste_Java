#
# Build stage
#
FROM maven:3.6.3-openjdk-16-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:16-jdk-alpine
#COPY target/cadastro-1.0.0.jar cadastro.jar
#ENTRYPOINT [ "java", "-jar", "/cadastro.jar" ]

COPY --from=build /home/app/target/cadastro-1.0.0.jar /usr/local/lib/cadastro.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/cadastro.jar"]