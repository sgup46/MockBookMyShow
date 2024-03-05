package com.sapient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.dao.IServiceDAO;
import com.sapient.dao.TheaterServiceDAO;
import com.sapient.domain.CityTheatre;
import com.sapient.model.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Data
public class TheaterService implements IService {

	Logger logger = LoggerFactory.getLogger(TheaterService.class);
	@Autowired
	@Qualifier("theaterServiceDAO")
	IServiceDAO theaterServiceDAO;

	@Autowired
	@Qualifier("cityService")
	IService theCityService;


	public Theater save(CityTheatre cityTheatre) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonString1 = "{\"area\":\"Koramangala tes\",\"city\":\"1\",\"name\":\"Finox mall\"}";
//		LinkedHashMap<String, String> jsonMap = new ObjectMapper().readValue(jsonString, LinkedHashMap.class);
//		Theater theTheater = new Theater();
//		for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
//			String key = entry.getKey();
//			Object value = entry.getValue();
//			if("name".equalsIgnoreCase(key)) {
//				theTheater.setTheater_name((String)value);
//			}
//			if("area".equalsIgnoreCase(key)) {
//				theTheater.setTheater_area((String)value);
//			}
//			if("city".equalsIgnoreCase(key)) {
//				City theCity = ((CityService)theCityService).findOne(Long.valueOf(String.valueOf(value)));
//				theTheater.setCity(theCity);
//			}
//		}

		Theater theTheater = new Theater();
		theTheater.setTheater_name(cityTheatre.getName());
		theTheater.setTheater_area(cityTheatre.getArea());
		City theCity = ((CityService)theCityService).findOne(cityTheatre.getCityId());
		theTheater.setCity(theCity);
		return ((TheaterServiceDAO)theaterServiceDAO).save(theTheater);
	}

	public Theater findOne(long ID){
		
		return ((TheaterServiceDAO)theaterServiceDAO).findOne(ID);
		
	}

	public List<Theater> getTheaterByCityId(City c){
		return ((TheaterServiceDAO)theaterServiceDAO).getTheaterByCityId(c);
	}

	public List<Theater> getTheatresByMovieAndShowTimings(long ID, String movieName, String showTime, String showDate){
		List<Object[]> theatres =  ((TheaterServiceDAO)theaterServiceDAO).getTheatresByMovieAndShowTimings(ID, movieName, showTime, showDate);
		logger.info("Theater Size received:" + theatres.size());
		List<Theater> listTheatres = new ArrayList<>();
		if (!CollectionUtils.isEmpty(theatres)) {
			theatres.stream().forEach( theatreObjectArray -> {
                 Theater t = new Theater();
				 t.setTheater_id(((BigInteger) theatreObjectArray[0]).longValue());
				t.setTheater_name((String) theatreObjectArray[1]);
				t.setTheater_area((String) theatreObjectArray[2]);
				listTheatres.add(t);
			});
		}
		return listTheatres;
	}
	

}
