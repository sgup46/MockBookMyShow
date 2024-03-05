package com.sapient.repository;

import com.sapient.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

	public List<Theater> findByCity(City ID);

	@Query(value = "  select t.Theater_id, t.theater_name,t.theater_area from THEATER t, movie m , show s  " +
			"where s.the_movie_movie_id=m.movie_id and s.the_theater_theater_id= t.theater_id\n" +
			" and t.city_city_id =?1 and m.movie_name=?2  and s.show_time =?3 and s.show_date=?4", nativeQuery = true)
	public List<Object[]> getTheatresByMovieAndShowTimings(long ID, String movieName, String showTime, String showDate);


}
