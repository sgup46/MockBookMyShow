package com.sapient.dao;

import com.sapient.model.*;
import com.sapient.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingsServiceDAO implements IServiceDAO {

	@Autowired
	BookingsRepository theBookingsRepository;
	
	public Booking getAvailableSeat(long showID) {
		return theBookingsRepository.getOne(showID);
	}
	
	public Booking bookTheSeat(Booking book) {
		return theBookingsRepository.save(book);
	}
	
}
