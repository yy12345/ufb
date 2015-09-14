package com.ufufund.ufb.biz.manager.org.impl.helper;

import com.ufufund.ufb.model.action.org.CreateOrgPlanAction1;
import com.ufufund.ufb.model.action.org.CreateOrgPlanAction3;
import com.ufufund.ufb.model.db.Orggplan;
import com.ufufund.ufb.model.db.Orggplandetailcharge;



public class OrgPlanHelper {
	
	public static Orggplan converntOrggplan(CreateOrgPlanAction1 action){
		Orggplan orggplan = new Orggplan();
		orggplan.setOrgid(action.getOrgid());
		//orggplan.setPlanid(String planid);
		orggplan.setGradeid(action.getGradeid());
		orggplan.setTermid(action.getTermid());
		orggplan.setPlanname(action.getPlanname());
		orggplan.setPlantype(action.getPlantype());
		orggplan.setCycletype(action.getCycletype());
		orggplan.setType(action.getType());
		orggplan.setAckdat(action.getAckdat());
		orggplan.setDat(action.getDat());
//		orggplan.setLastdate(String lastdate);
//		orggplan.setNextdate(String nextdate);
//		orggplan.setStats(String stats);
//		
		orggplan.setReplanid(action.getReplanid());
		//orggplan.setRemark1(String remark1);
		//orggplan.setRemark2(String remark2);
		orggplan.setCreateno(action.getCreateno());
		//orggplan.setUpdateno(String updateno);
		return orggplan;
	}
	
	public static Orggplandetailcharge converntOrggplandetailcharge(CreateOrgPlanAction3 action){
		Orggplandetailcharge orggplandetailcharge = new Orggplandetailcharge();
		orggplandetailcharge.setChargeid(action.getChargeid());
		orggplandetailcharge.setChargetype(action.getChargetype());
		orggplandetailcharge.setChargename(action.getChargename());
		orggplandetailcharge.setChargeamount(action.getChargename());
		orggplandetailcharge.setCycle(action.getCycle());
		return orggplandetailcharge;
	}
}
