package com.ufufund.ufb.biz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.dao.CityMapper;
import com.ufufund.ufb.model.db.City;

@Service
public class CityManager{

	@Autowired
	private CityMapper cityMapper;
	
	/**
	 * 获得所有的省份信息
	 * @return
	 */
	public List<City> getAllProvince() {
		return cityMapper.getAllProvince();
	}

	/**
	 * 根据省份信息获得城市的信息
	 * @param city
	 * @return
	 */
	public List<City> getCityByProvince(City city) {
		return cityMapper.getCityByProvince(city);
	}

}
