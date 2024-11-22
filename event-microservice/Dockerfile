FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN apk add --no-cache wget curl bash

COPY target/event-microservice-1.0.0.jar app.jar

COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

EXPOSE 8082

ENTRYPOINT ["./docker-entrypoint.sh"]