package com.ufufund.ufb.dao;

import java.util.List;

import com.ufufund.ufb.common.dao.BaseDao;
import com.ufufund.ufb.model.db.Orgchargeinfo;
import com.ufufund.ufb.model.db.Orggplan;
import com.ufufund.ufb.model.db.Orggplandetail;
import com.ufufund.ufb.model.db.Orggplandetailcharge;
import com.ufufund.ufb.model.db.Orggrade;


public interface OrgDeployMapper extends BaseDao {
	
	
//	/**
//	 * 查询手机号是否注册
//	 * @param CustinfoAction custinfoAction
//	 * @return 
//	 * 
//	 * rownum  = 1;
//	 */
//	public Custinfo getCustinfo(Custinfo custinfo);
//	
//	/*
//	 * 不使用  ，使用CustManager insterCustinfo方法
//	 */
//	public void insertCustinfo(Custinfo custinfo);
//	
//	
//	public void updateCustinfo(Custinfo custinfo);
	
	public Orggrade getOrgGradeInfo(Orggrade orggrade);
	public void mergeOrgGradeInfo(Orggrade orggrade);
	public List<Orgchargeinfo> getOrgchargeinfo(Orgchargeinfo orgchargeinfo);
	public int createOrgchargeinfo(Orgchargeinfo orgchargeinfo);
	public int updateOrgchargeinfo(Orgchargeinfo orgchargeinfo);
	
	
	public int deleteOrggplan(String planid);
	public int deleteOrggplandetail(String planid);
	public int deleteOrggplandetailcharge(String planid);
	public int insertOrggplan(Orggplan orggplan);
	public int insertOrggplandetailList(List<Orggplandetail> orggplandetailList);
	public int insertOrggplandetailchargeList(List<Orggplandetailcharge> orggplandetailchargeList);
	
	public List<Orggplan> getOrggplan(Orggplan orggplan);
	
}
