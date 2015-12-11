package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.model.db.OrgPriceItem;
import com.ufufund.ufb.model.db.OrgQuery;

public interface OrgQueryItem {

	/**
	 * 根据xxx查询用户收费明细
	 * @param custno
	 * @return
	 */
	public List<OrgPriceItem> queryXXXByXXX();

}
