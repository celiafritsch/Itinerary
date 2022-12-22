package it.unitn.fritsch.itinerary;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path="/api/v1/itinerary")
public class ItineraryController {
    private final ItineraryServiceGeoapify itineraryServiceGeoapify;

    //http://localhost:9080/api/v1/itinerary/transit?startLat=46.06672925347145&startLong=11.149817900662818&endLat=46.06726990853974&endLong=11.121434990336198
    @Autowired
    public ItineraryController(ItineraryServiceGeoapify itineraryServiceGeoapify) {
        this.itineraryServiceGeoapify = itineraryServiceGeoapify;
    }

  /*  @GetMapping()
    public String getDriving(){
        return itineraryServiceORS.getItinerary();
        //return "Hello world";
    }*/

    @GetMapping("/{mode}")
    public Map<String, Integer> getRouteInfo(@PathVariable String mode, @RequestParam("startLat") double startLat, @RequestParam("startLong") double startLong, @RequestParam("endLat") double endLat, @RequestParam("endLong") double endLong) throws JsonProcessingException {
        return itineraryServiceGeoapify.getInfo(startLat, startLong, endLat, endLong, mode);
    }


}
