package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Picinfo;

public interface PicinfoManager {

	/**
	 * 添加图片信息
	 * @param picinfo
	 * @return
	 */
	int addPicinfo(Picinfo picinfo);
}
