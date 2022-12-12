package it.unitn.fritsch.itinerary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
public class ItineraryServiceHere {
    private final RestTemplate restTemplate;

    private final String base = "https://transit.router.hereapi.com/v8/routes";

    @Value("${app.property.api-key-here}")
    private String api_key;

    @Autowired
    public ItineraryServiceHere(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getItinerary(){
       /* HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+api_key);
        String url = base+"?api_key="+api_key+"&start=8.681495,49.41461&end=8.687872,49.420318";
        return this.restTemplate.getForObject(url, String.class);*/

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //headers.set("User-Agent", "Spring's RestTemplate" );  // value can be whatever
        headers.set("Authorization", "Bearer "+api_key );
        String param = "?origin=41.79457,12.25473&destination=41.90096,12.50243";
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                base+request,
                HttpMethod.GET,
                request,
                String.class
        );

        return response.getBody();
    }

}
