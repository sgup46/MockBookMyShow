package com.sapient.controller;

import com.sapient.exception.TicketBookingException;
import com.sapient.model.*;
import com.sapient.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Data
public class B2CContoller {


	@Autowired
	@Qualifier("cityService")
	IService theCityService;

	@Autowired
	@Qualifier("movieService")
	IService theMovieService;

	@Autowired
	@Qualifier("theaterService")
	IService theTheaterService;

	@Autowired
	@Qualifier("showService")
	IService theShowService;



	/**
	 * Get the list of cities
	 * @return
	 */
	@GetMapping("/city")
	public List<City> getAllCity(){
		
		return ((CityService)theCityService).getCity();
	}


	/**
	 * get the theatre in city
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/theater")
	public List<Theater> getTheaterInCity(@PathVariable(value = "id") long id){
		
		City theCity = ((CityService)theCityService).findOne(id);
		
		return ((TheaterService)theTheaterService).getTheaterByCityId(theCity);
	}

	/**
	 * Get the movies by TheatreId
	 * @param ID
	 * @return
	 */
	@GetMapping("/info/theater/{ID}/movie/")
	public List<Movie> getMovieByTheaterID(@PathVariable(value = "ID") long ID){

		return ((MovieService)theMovieService).getMovieByTheaterId(ID);
	}


	/**
	 * Get the shows by movieId
	 * @param ID
	 * @return
	 */
	@GetMapping("/movie/{ID}/show")
	public List<Show> geShowByMovieId(@PathVariable(value = "ID") long ID){
		
		Movie theMovie = ((MovieService)theMovieService).findOne(ID);
	
		return ((ShowService)theShowService).fetchByMovie(theMovie);
	}

	/**
	 * get the theatre in city
	 * @param ID
	 * @return
	 */
	@GetMapping("/info/theatres/{ID}/{movieName}/{showDate}/{showTime}")
	public List<Theater> getTheatresByMovieAndShowTimings(@PathVariable(value = "ID") long ID, @PathVariable(value = "movieName") String movieName,
										  @PathVariable(value = "showTime") String showTime, @PathVariable(value = "showDate") String showDate){
		return ((TheaterService)theTheaterService).getTheatresByMovieAndShowTimings(ID , movieName, showTime, showDate);
	}


	
}






