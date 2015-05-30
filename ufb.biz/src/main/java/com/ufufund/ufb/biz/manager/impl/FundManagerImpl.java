package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.dao.FundInfoMapper;
import com.ufufund.ufb.model.db.FundInfo;

@Service
public class FundManagerImpl implements FundManager{

	@Autowired
	private FundInfoMapper fundInfoMapper;
	
	@Override
	public FundInfo getFundInfo(FundInfo fundInfo) {
		return fundInfoMapper.getFundInfo(fundInfo);
	}

}
