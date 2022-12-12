package it.unitn.fritsch.itinerary;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/itinerary")
public class ItineraryController {
    private final ItineraryServiceGeoapify itineraryServiceGeoapify;

    @Autowired
    public ItineraryController(ItineraryServiceGeoapify itineraryServiceGeoapify) {
        this.itineraryServiceGeoapify = itineraryServiceGeoapify;
    }

  /*  @GetMapping()
    public String getDriving(){
        return itineraryServiceORS.getItinerary();
        //return "Hello world";
    }*/

    @GetMapping
    public int getPublicTransport() throws JsonProcessingException {
        return itineraryServiceGeoapify.getTime();
    }

}
