package com.ufufund.ufb.dao;

import com.ufufund.ufb.model.db.Picinfo;

public interface PicinfoMapper {
	
	int addPicinfo(Picinfo picinfo);
	
	Picinfo getPicinfo(String custno);
	
	void deletePicinfo(String custno);
	

}
