package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.CityManager;
import com.ufufund.ufb.dao.CityMapper;
import com.ufufund.ufb.model.db.City;

@Service
public class CityManagerImpl implements CityManager{

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public List<City> getAllProvince() {
		return cityMapper.getAllProvince();
	}

	@Override
	public List<City> getCityByProvince(City city) {
		return cityMapper.getCityByProvince(city);
	}

}
