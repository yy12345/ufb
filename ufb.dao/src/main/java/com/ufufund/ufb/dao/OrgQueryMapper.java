package com.ufufund.ufb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.vo.QueryOrggplan;
import com.ufufund.ufb.model.vo.QueryOrggplandetail;
import com.ufufund.ufb.model.vo.QueryOrggplandetailcharge;


public interface OrgQueryMapper extends BaseDao {
	
	

	public List<QueryOrggplan> getQueryOrggplan(@Param("orgid")String orgid);
	
	
	public List<QueryOrggplandetail> getQueryOrggplandetail(
			@Param("orgid")String orgid,
			@Param("planid")String planid
			);
	
	public List<QueryOrggplandetailcharge> getQueryOrggplandetailcharge(
			@Param("orgid")String orgid,
			@Param("planid")String planid,
			@Param("detailid")String detailid
			);
}
