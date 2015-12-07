package com.ufufund.ufb.biz.manager.org.impl.helper;

import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.db.Orgplan;
import com.ufufund.ufb.model.db.Orgplandetailcharge;



public class OrgPlanHelper {
	
	public static Orgplan converntOrggplan(CreateOrgPlanAction1 action){
		Orgplan orggplan = new Orgplan();
	/*	orggplan.setOrgid(action.getOrgid());
		//orggplan.setPlanid(String planid);
		orggplan.setGradeid(action.getGradeid());
		orggplan.setTermid(action.getTermid());
		orggplan.setPlanname(action.getPlanname());
		orggplan.setPlantype(action.getPlantype());
		orggplan.setCycletype(action.getCycletype());
		orggplan.setType(action.getType());
		orggplan.setAckdat(action.getAckdat());
		orggplan.setDat(action.getDat());
		orggplan.setPaydate(action.getPaydate());
//		orggplan.setStats(String stats);
//		
		orggplan.setReplanid(action.getReplanid());
		//orggplan.setRemark1(String remark1);
		//orggplan.setRemark2(String remark2);
		orggplan.setCreateno(action.getCreateno());
		//orggplan.setUpdateno(String updateno);
*/		return orggplan;
	}
	
	public static Orgplandetailcharge converntOrggplandetailcharge(CreateOrgPlanAction3 action){
		Orgplandetailcharge orggplandetailcharge = new Orgplandetailcharge();
		orggplandetailcharge.setChargeid(action.getChargeid());
		orggplandetailcharge.setChargetype(action.getChargetype());
		orggplandetailcharge.setChargename(action.getChargename());
		orggplandetailcharge.setChargeamount(action.getChargeamount());
		orggplandetailcharge.setCycle(action.getCycle());
		return orggplandetailcharge;
	}
}
