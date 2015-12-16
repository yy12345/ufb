package com.ufufund.ufb.biz.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.dao.FundInfoMapper;
import com.ufufund.ufb.model.db.FundInfo;

/**
 * 基金产品及其它有关基金的业务接口定义类
 * @author ayis
 * 2015年5月30日
 */
@Service
public class FundManager{

	@Autowired
	private FundInfoMapper fundInfoMapper;
	
	/**
	 * 查询基金产品详情
	 * @param fundInfo
	 * @return
	 */
	public FundInfo getFundInfo(FundInfo fundInfo) {
		return fundInfoMapper.getFundInfo(fundInfo);
	}

}
