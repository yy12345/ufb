package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Orginfo;
import com.ufufund.ufb.model.db.Picinfo;

public interface OrganManager {

	/**
	 * 添加机构信息
	 * @param orginfo
	 * @return
	 */
	int addOrginfo(Orginfo orginfo);
	
	/**
	 * 添加图片信息
	 * @param picinfo
	 * @return
	 */
	int addPicinfo(Picinfo picinfo);
	
	/**
	 * 获得机构信息
	 * @param orginfo
	 * @return
	 */
	Orginfo getOrginfo(Orginfo orginfo);
	
	/**
	 * 手机号是否注册
	 * @param orginfo
	 * @return
	 */
	boolean isMobileRegister(Orginfo orginfo);
}
