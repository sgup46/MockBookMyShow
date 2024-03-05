package com.sapient.service;

import com.sapient.dao.CityServiceDAO;
import com.sapient.dao.IServiceDAO;
import com.sapient.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
public class CityService implements IService {
	
	@Autowired
	@Qualifier("cityServiceDAO")
	IServiceDAO cityServiceDAO;

	/**
	 * Will save the city instance
	 * @param c
	 * @return
	 */
	public City save(City c) {
		return ((CityServiceDAO)cityServiceDAO).save(c);
	}


	/**
	 * find all the cities
	 * @return
	 */
	public List<City> getCity( ){
		return ((CityServiceDAO)cityServiceDAO).getCity();
	}


	public City findOne(long cityID) {
		City c = ((CityServiceDAO)cityServiceDAO).findOne(cityID);
		return c;
	}

	

}
