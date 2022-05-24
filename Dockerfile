FROM openjdk:18

COPY ./build/libs/ec-carts-api-0.0.1-SNAPSHOT.jar ec-carts-api-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "ec-carts-api-0.0.1-SNAPSHOT.jar"]