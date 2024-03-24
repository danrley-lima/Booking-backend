FROM eclipse-temurin:21-jdk-alpine

WORKDIR /workspace/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

RUN chmod 700 .

CMD ["./mvnw", "spring-boot:run"]