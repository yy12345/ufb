package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.action.org.QueryOrggplan;
import com.ufufund.ufb.model.db.Orgchargeinfo;
import com.ufufund.ufb.model.db.Orggplan;
import com.ufufund.ufb.model.db.Orggplandetail;
import com.ufufund.ufb.model.db.Orggplandetailcharge;
import com.ufufund.ufb.model.db.Orggrade;


public interface OrgQueryMapper extends BaseDao {
	
	

	public List<QueryOrggplan> getQueryOrggplan(@Param("orgid")String orgid);
	
	
}
