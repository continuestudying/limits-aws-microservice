FROM openjdk:8-jdk-alpine

COPY ./target/zuul-gateway.jar /app/
CMD ["java", "-jar", "app/zuul-gateway.jar"]

EXPOSE 8765