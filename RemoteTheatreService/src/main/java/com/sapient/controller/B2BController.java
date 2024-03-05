package com.sapient.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.config.service.IService;
import com.sapient.domain.CityTheatre;
import com.sapient.domain.MovieShow;
import com.sapient.messaging.KafkaProducerService;
import com.sapient.model.*;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@Data
public class B2BController {


	@Autowired
	@Qualifier("bookingsService")
	IService theBookingsService;

	@Autowired
	@Qualifier("kafkaProducerService")
	KafkaProducerService producerService;


	/**
	 * Create the city
	 * @param c
	 * @return
	 */
	@PostMapping("/createCity")
	public ResponseEntity<String> createCity(@Valid @RequestBody City c) {
		log.info("Sending event to vendor-event {} ", c);
		producerService.sendObjectMessage("city", c);
		return ResponseEntity.ok("OK");
	}


	/**
	 * Create the movie
	 * @param m
	 * @return
	 */
	@PostMapping("/createMovie")
	public ResponseEntity<String> createMovie(@Valid @RequestBody Movie m) {
		log.info("Sending event to vendor-event {} ", m);
		producerService.sendObjectMessage("movie", m);
		return ResponseEntity.ok("OK");
	}


	/**
	 * Create a theatre in city
	 * @return
	 */
	@PostMapping("/createTheaterInCity")
	public ResponseEntity<String> createTheaterInCity(@Valid @RequestBody CityTheatre cityTheatre) throws JsonProcessingException {
		log.info("Sending event to vendor-event {} ", cityTheatre);
		producerService.sendObjectMessage("city_theatre", cityTheatre);
		return ResponseEntity.ok("OK");

	}

	/**
	 * create a new show to the theatreId  and movieId
	 * @return
	 */
	@PostMapping("/createUpdateOrDeleteShow")
	public ResponseEntity<String> addShowToTheaterForMovie(@Valid @RequestBody MovieShow movieShow) throws JsonProcessingException {
		log.info("Sending event to vendor-event {} ", movieShow);
		producerService.sendObjectMessage("show", movieShow);
		return ResponseEntity.ok("OK");
	}


}










