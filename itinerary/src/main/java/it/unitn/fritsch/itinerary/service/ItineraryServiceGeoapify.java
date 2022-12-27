package it.unitn.fritsch.itinerary.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import it.unitn.fritsch.itinerary.model.Itinerary;
import it.unitn.fritsch.itinerary.model.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    public String getItinerary(double startLat, double startLong, double endLat, double endLong, String mode){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", api_key );
        String s = "?waypoints="+startLat+","+startLong+"|"+endLat+","+endLong+"&mode="+mode;
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                base+s,
                HttpMethod.GET,
                request,
                String.class
        );

        return response.getBody();
    }

    public Itinerary getInfo(double startLat, double startLong, double endLat, double endLong, String mode) throws JsonProcessingException {
        ObjectReader reader = new ObjectMapper().readerFor(Map.class);
        Map<String, Object> map = reader.readValue(getItinerary(startLat, startLong, endLat, endLong, mode));
        List features = (List) map.get("features");
        System.out.println("features");
        //System.out.println(features) ;
        Object properties = ((Map)(features).get(0)).get("properties");
        //System.out.println(properties);
        //System.out.println(map);
        //Map<String, Integer> info = new HashMap<>();
        //System.out.println(((Map)properties).get("time").getClass());
        Itinerary itinerary = new Itinerary();
        if (((Map)properties).get("time").getClass().equals(Double.class)){
            //info.put("time", ((Double)((Map)properties).get("time")).intValue());
            itinerary.setTime(((Double)((Map)properties).get("time")).intValue());
        }
        else {
            //info.put("time", (Integer)((Map)properties).get("time"));
            itinerary.setTime((Integer)((Map)properties).get("time"));
        }
        //info.put("distance", (Integer) ((Map)properties).get("distance"));
        itinerary.setDist((Integer) ((Map)properties).get("distance"));
        Object geometry = ((Map)((List) map.get("features")).get(0)).get("geometry");
        Object coordinates = ((Map)geometry).get("coordinates");
        //System.out.println(((List)coordinates).get(0));
        ArrayList<Position> positions = new ArrayList();
        for (Object sublist:(List)((List)coordinates).get(0)){
            positions.add(new Position((double)((List)sublist).get(1), (double)((List)sublist).get(0)));
        }
        itinerary.setPosition(positions);
        return itinerary;
    }
}
