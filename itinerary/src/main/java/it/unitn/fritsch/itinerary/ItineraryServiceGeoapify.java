package it.unitn.fritsch.itinerary;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ItineraryServiceGeoapify {
    //https://apidocs.geoapify.com/docs/routing/#routing
    //https://apidocs.geoapify.com/playground/routing/
    private final RestTemplate restTemplate;
    @Value("${app.property.api-key-geoapify}")
    private String api_key;

    private final String base = "https://api.geoapify.com/v1/routing";


    public ItineraryServiceGeoapify(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getItinerary(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", api_key );
        String s = "?waypoints=41.89427704160718,12.480640574647623|41.8902614,12.493087103595503&mode=transit";
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                base+s,
                HttpMethod.GET,
                request,
                String.class
        );

        return response.getBody();
    }

    public int getTime() throws JsonProcessingException {
        ObjectReader reader = new ObjectMapper().readerFor(Map.class);
        Map<String, Object> map = reader.readValue(getItinerary());
        Object properties = ((Map)((List) map.get("features")).get(0)).get("properties");
        System.out.println(properties);
        System.out.println(map.toString());
        return (int)((Map)properties).get("time");
    }
}
