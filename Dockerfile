FROM openjdk:17

COPY target/itinerary.jar itinerary.jar

ENTRYPOINT ["java","-jar","itinerary.jar"]