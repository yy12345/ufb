package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.model.db.City;

public interface CityMapper {
	
	List<City> getAllProvince();
	
	List<City> getCityByProvince(City city);
}
