package it.unitn.fritsch.itinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ItineraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItineraryApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
