package com.sapient.service;

import com.sapient.dao.BookingsServiceDAO;
import com.sapient.dao.IServiceDAO;
import com.sapient.model.Booking;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Data
public class BookingsService implements IService {

	@Autowired
	@Qualifier("bookingsServiceDAO")
	IServiceDAO bookingsServiceDAO;
	
	public Booking getAvailableSeat(long showID) {
		return ((BookingsServiceDAO)bookingsServiceDAO).getAvailableSeat(showID);
	}
	
	public Booking bookTheSeat(Booking book) {

		return ((BookingsServiceDAO)bookingsServiceDAO).bookTheSeat(book);
	}
	
}
