package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.BankBaseInfo;

public interface BankBaseMapper extends BaseDao {
	
	public List<BankBaseInfo> getBankBaseInfo(BankBaseInfo bankBaseInfo);
	
}
