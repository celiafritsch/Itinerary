package it.unitn.fritsch.itinerary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/itinerary")
public class ItineraryController {
    @GetMapping
    public String hello(){
        return "Hello world";
    }
}
