FROM eclipse-temurin:21-jdk-alpine

WORKDIR /workspace/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

RUN apk update && \
  apk add --no-cache git && \
  rm -rf /var/cache/apk/*

COPY src ./src

RUN chmod 700 .

CMD ["./mvnw", "spring-boot:run"]