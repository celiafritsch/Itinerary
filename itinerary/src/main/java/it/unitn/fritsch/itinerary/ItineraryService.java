package it.unitn.fritsch.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItineraryService {
   private final RestTemplate restTemplate;
   @Value("${app.property.api-key}")
   private String api_key;
   @Autowired
    public ItineraryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPostsPlainJSON() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }

    public String getItinerary(){
       String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key="+api_key+"&start=8.681495,49.41461&end=8.687872,49.420318";
       return this.restTemplate.getForObject(url, String.class);
    }

    /*public String getDuration(){
       String url =
    }*/
}
