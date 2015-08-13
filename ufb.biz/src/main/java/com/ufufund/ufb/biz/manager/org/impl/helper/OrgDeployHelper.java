package com.ufufund.ufb.biz.manager.org.impl.helper;

import com.ufufund.ufb.model.action.org.CreateOrgchargeinfoAction;
import com.ufufund.ufb.model.action.org.UpdateOrgchargeinfoAction;
import com.ufufund.ufb.model.db.Orgchargeinfo;



public class OrgDeployHelper {
	
	public static Orgchargeinfo converntOrgchargeinfo(CreateOrgchargeinfoAction action){
		Orgchargeinfo reaction = new Orgchargeinfo();
		reaction.setOrgid(action.getOrgid());
		//reaction.setChargeid(action.getChargeid())//	char	24	主键
		reaction.setChargetype(action.getChargetype());//	char	1	计费类型
		reaction.setChargename(action.getChargename());//	varchar	30	名称
		reaction.setChargeamount(action.getChargeamount());//	number	16,2	默认金额
		reaction.setCycle(action.getCycle());//	char	1	计费周期 学年/学期/每月
		reaction.setCreateno(action.getCreateno());//	char	1	计费周期 学年/学期/每月
		return reaction;
	}
	public static Orgchargeinfo converntOrgchargeinfo(UpdateOrgchargeinfoAction action){
		Orgchargeinfo reaction = new Orgchargeinfo();
		reaction.setOrgid(action.getOrgid());
		reaction.setChargeid(action.getChargeid());//	char	24	主键
		reaction.setChargetype(action.getChargetype());//	char	1	计费类型
		reaction.setChargename(action.getChargename());//	varchar	30	名称
		reaction.setChargeamount(action.getChargeamount());//	number	16,2	默认金额
		reaction.setCycle(action.getCycle());//	char	1	计费周期 学年/学期/每月
		reaction.setUpdateno(action.getCreateno());//	char	1	计费周期 学年/学期/每月
		return reaction;
	}
	
}
