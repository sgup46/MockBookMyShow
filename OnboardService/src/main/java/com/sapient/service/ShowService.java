package com.sapient.service;

import com.sapient.dao.IServiceDAO;
import com.sapient.dao.MovieServiceDAO;
import com.sapient.dao.ShowServiceDAO;
import com.sapient.dao.TheaterServiceDAO;

import com.sapient.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Data
public class ShowService implements IService {


	@Autowired
	@Qualifier("movieServiceDAO")
	IServiceDAO movieServiceDAO;

	@Autowired
	@Qualifier("theaterServiceDAO")
	IServiceDAO theaterServiceDAO;

	@Autowired
	@Qualifier("showServiceDAO")
	IServiceDAO showServiceDAO;
	
	public Show createShow(long ID, long movie_id, String show_time)
	{
		Theater theTheater = ((TheaterServiceDAO)theaterServiceDAO).findOne(ID);
		Movie theMovie = ((MovieServiceDAO)movieServiceDAO).findOne(movie_id);
		Show theShow = new Show();
		theShow.setShow_time(show_time);
		theShow.setShow_date(new Date());
		theShow.setTheMovie(theMovie);
		theShow.setTheTheater(theTheater);
		return ((ShowServiceDAO)showServiceDAO).save(theShow);
	}
	
	public List<Show> fetchAllShow(){
		return ((ShowServiceDAO)showServiceDAO).fetchAllShow();
		
	}

	public boolean deleteShow(long id, long movie_id) {
		Theater theTheater = ((TheaterServiceDAO)theaterServiceDAO).findOne(id);
		Movie theMovie = ((MovieServiceDAO)movieServiceDAO).findOne(movie_id);
		Show theShow = theMovie.getTheShows().stream().findFirst().get();
		((ShowServiceDAO)showServiceDAO).delete(theShow);
		return true;
	}

	public List<Show> fetchByMovie(Movie m){
		return ((ShowServiceDAO)showServiceDAO).fetchByMovie(m);
	}

	public Show updateShow(long id, long movie_id, String show_time) {
		Theater theTheater = ((TheaterServiceDAO)theaterServiceDAO).findOne(id);
		Movie theMovie = ((MovieServiceDAO)movieServiceDAO).findOne(movie_id);
		Show theShow = theMovie.getTheShows().stream().findFirst().get();
		theShow.setShow_time(show_time);
		return ((ShowServiceDAO)showServiceDAO).save(theShow);
	}
}
