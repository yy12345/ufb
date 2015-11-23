package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.City;

public interface CityManager {
	
	/**
	 * 获得所有的省份信息
	 * @return
	 */
	List<City> getAllProvince();
	
	/**
	 * 根据省份信息获得城市的信息
	 * @param city
	 * @return
	 */
	List<City> getCityByProvince(City city);

}
