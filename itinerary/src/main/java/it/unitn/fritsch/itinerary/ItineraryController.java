package it.unitn.fritsch.itinerary;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="/api/v1/itinerary")
public class ItineraryController {
    private final ItineraryServiceGeoapify itineraryServiceGeoapify;
    private double startLat = 46.06672925347145;
    private double startLong = 11.149817900662818;
    private double endLat = 46.06726990853974;
    private double endLong = 11.121434990336198;

    @Autowired
    public ItineraryController(ItineraryServiceGeoapify itineraryServiceGeoapify) {
        this.itineraryServiceGeoapify = itineraryServiceGeoapify;
    }

  /*  @GetMapping()
    public String getDriving(){
        return itineraryServiceORS.getItinerary();
        //return "Hello world";
    }*/

    @GetMapping("/transit")
    public Map<String, Integer> getByPublicTransport() throws JsonProcessingException {
        // Time
        //Distance
        // Number of change
        return itineraryServiceGeoapify.getInfo(startLat, startLong, endLat, endLong, "transit");
    }

    @GetMapping("/drive")
    public Map<String, Integer> getByDriving() throws JsonProcessingException {
        //Time
        //Distance
        return itineraryServiceGeoapify.getInfo(startLat, startLong, endLat, endLong, "drive");
    }

    @GetMapping("/walk")
    public Map<String, Integer> getByWalk() throws JsonProcessingException {
        return itineraryServiceGeoapify.getInfo(startLat, startLong, endLat, endLong, "walk");

    }


}
