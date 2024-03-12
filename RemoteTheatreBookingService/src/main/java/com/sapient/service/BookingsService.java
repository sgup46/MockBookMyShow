package com.sapient.service;

import com.sapient.dao.BookingsServiceDAO;
import com.sapient.dao.IServiceDAO;
import com.sapient.domain.ReserveSeat;
import com.sapient.model.Booking;
import lombok.Data;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@Data
public class BookingsService implements IService {

	@Autowired
	@Qualifier("bookingsServiceDAO")
	IServiceDAO bookingsServiceDAO;

	@Autowired
	private RedissonClient redissonClient;
	
	public Booking getAvailableSeat(long showID) {
		return ((BookingsServiceDAO)bookingsServiceDAO).getAvailableSeat(showID);
	}
	
	public Booking bookTheSeat(Booking book) {
		return ((BookingsServiceDAO)bookingsServiceDAO).bookTheSeat(book);
	}



	public boolean bookSeat(ReserveSeat reserveSeat) {
		RMap<String, String> myMap = redissonClient.getMap("myMap");
		RLock lock = redissonClient.getLock("reserve_" + reserveSeat.getTheatreId()
				+ reserveSeat.getShowId() + reserveSeat.getSeatId());
		try {
			boolean acquired = lock.tryLock(0, 10, TimeUnit.MINUTES); // Attempt to acquire lock without blocking
			if (acquired) {
				try {
					// DO THE BOOKING and then RELEASE THE LOCK
//					if (bookingRepository.canBeBooked(reserveSeat.getSeatId())) { // Check availability atomically
//						bookingRepository.book(reserveSeat.getSeatId());
						return true;
//					}
				} finally {
					lock.unlock();
				}
			}
		} catch (Exception e) {
			lock.unlock();
			return false;
		}
		return false;
	}


}
