package com.ufufund.ufb.biz.manager.org.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.manager.impl.ImplCommon;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.dao.OrgDeployMapper;
import com.ufufund.ufb.dao.OrgQueryMapper;
import com.ufufund.ufb.model.action.org.QueryOrggplan;
import com.ufufund.ufb.model.db.Orggplan;


@Service
public class OrgQueryManagerImpl extends ImplCommon implements OrgQueryManager {

	@Autowired
	private OrgQueryMapper orgQueryMapper;
	
	@Override
	public List<QueryOrggplan> getQueryOrggplan(String orgid) {
		// TODO Auto-generated method stub
		return orgQueryMapper.getQueryOrggplan(orgid);
	}

	
}
