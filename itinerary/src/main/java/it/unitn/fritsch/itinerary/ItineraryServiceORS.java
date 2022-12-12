package it.unitn.fritsch.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItineraryServiceORS {
   private final RestTemplate restTemplate;
   @Value("${app.property.api-key-ORS}")
   private String api_key;

   private final String base = "https://api.openrouteservice.org/v2/directions";
   @Autowired
    public ItineraryServiceORS(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getItinerary(){
       System.out.println(api_key);
       String url = base+"/driving-car?api_key="+api_key+"&start=8.681495,49.41461&end=8.687872,49.420318";
       return this.restTemplate.getForObject(url, String.class);
    }

    /*public String getDuration(){
       String url =
    }*/
}
