package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.model.db.FundInfo;

/**
 * 基金产品及其它有关基金的业务接口定义类
 * @author ayis
 * 2015年5月30日
 */
public interface FundManager {

	/**
	 * 查询基金产品详情
	 * @param fundInfo
	 * @return
	 */
	public FundInfo getFundInfo(FundInfo fundInfo);
}
