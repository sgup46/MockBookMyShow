package com.sapient.controller;



import com.sapient.domain.ReserveSeat;
import com.sapient.service.BookingsService;
import com.sapient.service.IService;
import com.sapient.messaging.KafkaProducerService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
@Data
public class B2BController {


	@Autowired
	@Qualifier("bookingsService")
	BookingsService theBookingsService;

	@Autowired
	@Qualifier("kafkaProducerService")
	KafkaProducerService producerService;



	/**
	 * Create the city
	 * @param c
	 * @return
	 */
	@PostMapping("/bookSeat")
	public ResponseEntity<Boolean> bookSeat(@Valid @RequestBody ReserveSeat c) {
		log.info("SGetting book event {} ", c);
		boolean success = theBookingsService.bookSeat(c);
		return ResponseEntity.ok(success);
	}



}










