package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.Orginfo;

public interface OrginfoManager {

	/**
	 * 添加机构信息
	 * @param orginfo
	 * @return
	 */
	int addOrginfo(Orginfo orginfo);
}
