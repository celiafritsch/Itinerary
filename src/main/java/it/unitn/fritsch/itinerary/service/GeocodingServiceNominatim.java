package it.unitn.fritsch.itinerary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unitn.fritsch.itinerary.model.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GeocodingServiceNominatim {
    private final RestTemplate restTemplate;

    private final String base = "https://nominatim.openstreetmap.org/search?format=geojson&";

    public GeocodingServiceNominatim(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> callAPI(String address){
        String url = base + "q=" + address;
        return (restTemplate.getForEntity(url, String.class));
    }

    public Position getPosition(String address) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(callAPI(address).getBody());
        JsonNode features = root.get("features");
        JsonNode geometry = (features.get(0)).get("geometry");
        JsonNode coordinates = geometry.get("coordinates");
        Position result = new Position(coordinates.get(1).asDouble(), coordinates.get(0).asDouble());
        return result;
    }
}
