package it.unitn.fritsch.itinerary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import it.unitn.fritsch.itinerary.model.Itinerary;
import it.unitn.fritsch.itinerary.service.GeocodingServiceNominatim;
import it.unitn.fritsch.itinerary.service.ItineraryServiceGeoapify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/itinerary")
public class ItineraryController {
    private final ItineraryServiceGeoapify itineraryServiceGeoapify;
    private final GeocodingServiceNominatim geocodingServiceNominatim;

    //http://localhost:9080/api/v1/itinerary/transit?startLat=46.06672925347145&startLong=11.149817900662818&endLat=46.06726990853974&endLong=11.121434990336198
    @Autowired
    public ItineraryController(ItineraryServiceGeoapify itineraryServiceGeoapify, GeocodingServiceNominatim geocodingServiceNominatim) {
        this.itineraryServiceGeoapify = itineraryServiceGeoapify;
        this.geocodingServiceNominatim = geocodingServiceNominatim;
    }


    @GetMapping("/{mode}")
    public String getRouteInfo(@PathVariable String mode, @RequestParam("startLat") double startLat, @RequestParam("startLong") double startLong, @RequestParam("endLat") double endLat, @RequestParam("endLong") double endLong) throws JsonProcessingException {
        Itinerary res = itineraryServiceGeoapify.getInfo(startLat, startLong, endLat, endLong, mode);
        return (new Gson().toJson(res));
    }

    @GetMapping("/geocode")
    public String getGeocode(@RequestParam("address") String address) throws JsonProcessingException {
        return new Gson().toJson(geocodingServiceNominatim.getPosition(address));
    }


}
