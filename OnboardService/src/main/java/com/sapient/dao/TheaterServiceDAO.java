package com.sapient.dao;

import com.sapient.model.*;
import com.sapient.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceDAO implements IServiceDAO{
	
	@Autowired
	TheaterRepository theTheaterRepository;

	public Theater save(Theater t) {
		return theTheaterRepository.save(t);
	}

	
	public Theater findOne(long ID){
		return theTheaterRepository.findById(ID).get();
		
	}

	public List<Theater> getTheaterByCityId(City c){
		return theTheaterRepository.findByCity(c);
	}

	public List<Object[]> getTheatresByMovieAndShowTimings(long ID, String movieName, String showTime, String showDate){
		return theTheaterRepository.getTheatresByMovieAndShowTimings(ID, movieName, showTime, showDate);
	}
	

}
