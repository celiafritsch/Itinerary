FROM openjdk:17

COPY target/Itinerary.jar itinerary.jar

ENTRYPOINT ["java","-jar","itinerary.jar"]

EXPOSE 9080
